package com.asiainfo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;import org.apache.log4j.helpers.MDCKeySetExtractor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.common.service.BaseExceptionService;
import com.asiainfo.entity.User;
import com.asiainfo.exception.ActiveAccountException;
import com.asiainfo.exception.MD5Exception;
import com.asiainfo.exception.ObjectToMapException;
import com.asiainfo.exception.SaveUserException;
import com.asiainfo.exception.SendEmailException;
import com.asiainfo.sso.service.SessionManager;
import com.asiainfo.sso.service.UserService;
import com.asiainfo.util.EmailUtil;
import com.asiainfo.util.ExceptionUtil;
import com.asiainfo.util.HttpUtil;
import com.asiainfo.util.JsonUtil;
import com.asiainfo.util.LoggerUtil;
import com.asiainfo.util.MD5Util;
import com.asiainfo.util.PageTemplate;
import com.asiainfo.util.RedisKey;
import com.asiainfo.util.WebResult;

@RestController
public class SsoController {
	@Resource
	private UserService userService;
	@Resource
	private BaseExceptionService baseExceptionService;
	@Resource
	private SessionManager sessionManager;
	
	
	@RequestMapping("/applyAccount")
	public String applyAccount(User user){
		
		
		
		//先判断该邮箱是否已经被注册过了
		int count = this.userService.queryUserCountByEmail(user.getEmail());
		if(count>0){
			return WebResult.Email_UNAVAILABLE;
		}
		
		
		String account=null;
		try {
			//提交账号申请后，将数据插入数据库，但激活状态设为未激活
			account=userService.saveUser(user);
			
			
		} catch (SaveUserException e) {
			e.setMessage(ExceptionUtil.getExceptionMessage(e));
			baseExceptionService.saveBaseException(e);
			return WebResult.APPLY_ACCOUNT_FAIL;
		} catch (MD5Exception e) {
			
			baseExceptionService.saveBaseException(e);
			return WebResult.APPLY_ACCOUNT_FAIL;
		} 
		
		//设置账号
		user.setAccount(account);
		
		
		//再调用远程发邮件服务，将账号发送到注册邮箱，并附带一条激活此账号的链接，三天内有效
		try {
			userService.sendActiveAccountEmail(user);
			return WebResult.APPLY_ACCOUNT_SUCCESS;
		} catch (SendEmailException e) {
			baseExceptionService.saveBaseException(e);
			return WebResult.SEND_EMAIL_FAIL;
		}
		
		
	}

	
	

	@RequestMapping("/activeAccount")
	public String activeAccount(String account){
		//先查询该账户是否已激活
		String s = this.userService.isUserAvailable(account);
		if("yes".equals(s)){
			return PageTemplate.getActiveAccountPage(WebResult.Account_Activated);
		}
		
		try {
			String date = this.userService.activeAccount(account);
			//如果账号激活成功，就向redis存入用户登录状态的key,且初始设置为离线
			String res = this.sessionManager.saveUserStatus(account, 0);
			//激活账号成功后，调用fileController,为用户创建文件夹
			Map<String, String> params = new HashMap<String,String>();
			params.put("token", account);
			params.put("date", date);
			String res2 = HttpUtil.sendPost("http://localhost:8081/uploadUserRootDir", params);
			
			LoggerUtil.info(this.getClass(), "----------"+res2);
			
			return PageTemplate.getActiveAccountPage(WebResult.ACTIVE_ACCOUNT_SUCCESS);
		} catch (ActiveAccountException e) {
			e.setMessage(ExceptionUtil.getExceptionMessage(e));
			baseExceptionService.saveBaseException(e);
			return PageTemplate.getActiveAccountPage(WebResult.ACTIVE_ACCOUNT_FAIL);

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return ""; 
		
	}
	
	@RequestMapping("/login")
	public String login(String token,String password,String loginWay){
		
		LoggerUtil.info(this.getClass(),token);
		LoggerUtil.info(this.getClass(),password);
		LoggerUtil.info(this.getClass(),loginWay);
		
		//先根据token查询是否有该用户
		Map<String, String> param = new HashMap<String,String>();
		if(Integer.parseInt(loginWay)==0){
			
			param.put("account", token);
			param.put("email", "");
		}else{
			param.put("account", "");
			param.put("email", token);
		}
		User user = this.userService.queryUserByToken(param);
		if(user==null){
			return WebResult.USER_NOT_EXIST;
		}
		
		//判断密码是否正确
		//加密前台输入的密码
		String encrypt=null;
		try {
			encrypt = MD5Util.encrypt(password);
		} catch (Exception e) {
			this.baseExceptionService.saveBaseException(new MD5Exception(user.getAccount(),ExceptionUtil.getExceptionMessage(e)));
			return WebResult.LOGIN_FAIL;
		}
		if(!user.getPassword().equals(encrypt)){
			return WebResult.PASSWORD_ERROR;
		}
		
		//调用redis服务,查看登录状态
		int status = this.sessionManager.queryUserStatus(user.getAccount());
		if(status==0){
			//可以登录，将用户状态改为1
			String res = this.sessionManager.saveUserStatus(user.getAccount(), 1);
			//将用户信息刷新一遍
			
			
			
		}else if(status==1){
			return WebResult.IS_LOGGED_IN;
		}
		
		
		try {
			
			String res = this.sessionManager.refreshAuthCache(user);
			
			LoggerUtil.info(this.getClass(), res);
			return user.getAccount();
			
		} catch (ObjectToMapException e) {
			this.baseExceptionService.saveBaseException(e);
			return WebResult.LOGIN_FAIL;
		}
		
		
	}
	
	
	
	@RequestMapping("/getUserCache")
	public String getUserCache(String token){
		Map<String, String> userMap = this.sessionManager.queryUser(token);
		String jsonString = JsonUtil.mapToJsonString(userMap);
		return jsonString;
	}
	
	@RequestMapping("/getUserStatus")
	public String getUserStatus(String token){
		
		int status = this.sessionManager.queryUserStatus(token);
		return status+"";
	}
	
	@RequestMapping("/modifyUserNickname")
	public String modifyUserNickname(String account,String nickname){
		User user = new User();
		user.setAccount(account);
		user.setNickname(nickname);
		try {
			this.userService.modifyUserNickname(user);
		} catch (Exception e) {
			return WebResult.NICKNAME_MODIFY_FAIL;
		}
		
		return WebResult.NICKNAME_MODIFY_SUCCESS;
	}
	
	@RequestMapping("/clearAuthCache")
	public void clearAuthCache(String account){
		String key=RedisKey.TOKEN_KEY_PREFIX+account;
		this.sessionManager.clearCacheByKey(key);
	}
	
	@RequestMapping("/sendEmailToOldEmail")
	public String sendEmailToOldEmail(String account,String oldEmail,String newEmail){
		
		String title="解除绑定此邮箱";
		String receiver=oldEmail;
		
		String content=EmailUtil.getRemoveOldEmailTemplate(account, newEmail);
		try {
			EmailUtil.sendSimpleMail(title, receiver, content);
		} catch (Exception e) {
			
		}
		return WebResult.SEND_EMAIL_SUCCESS;
		
	}
	
	@RequestMapping("/sendEmailToNewEmail")
	public String sendEmailToNewEmail(String account,String newEmail){
		
		String title="绑定此邮箱";
		String receiver=newEmail;
		
		String content=EmailUtil.getBindNewEmailTemplate(account, newEmail);
		
		try {
			EmailUtil.sendSimpleMail(title, receiver, content);
		} catch (Exception e) {
			
		}
		return PageTemplate.getWindowClosePage(WebResult.SEND_EMAIL_SUCCESS);
		
	}
	
	@RequestMapping("/modifyUserEmail")
	public String modifyUserEmail(String account,String newEmail){
		
		User user = new User();
		user.setAccount(account);
		user.setEmail(newEmail);
		
		try {
			this.userService.modifyUserEmail(user);
		} catch (Exception e) {
		}
		
		return PageTemplate.getWindowClosePage(WebResult.MODIFY_EMAIL_SUCCESS);
		
	}
	
	@RequestMapping("/modifyUserPwd")
	public String modifyUserPwd(String account,String oldPwd,String newPwd){
		
		User user = this.userService.queryUserByAccount(account);
		String encrypt=null;
		try {
			encrypt = MD5Util.encrypt(oldPwd);
		} catch (Exception e) {
		}
		
		if(!encrypt.equals(user.getPassword())){
			return WebResult.OLD_PWD_ERROR;
		}
		
		try {
			user.setPassword(MD5Util.encrypt(newPwd));
		} catch (Exception e) {
		}
		
		try {
			this.userService.modifyUserPwd(user);
		} catch (Exception e) {
			return WebResult.PWD_MODIFY_FAIL;
		}
		return WebResult.PWD_MODIFY_SUCCESS;
	}
	
	@RequestMapping("/getStatus")
	public String getStatus(String token){
		int status = this.sessionManager.queryUserStatus(token);
		
		return status+"";
		
	}
	
}
