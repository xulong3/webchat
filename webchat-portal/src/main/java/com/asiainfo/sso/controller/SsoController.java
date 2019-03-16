package com.asiainfo.sso.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.common.service.BaseExceptionService;
import com.asiainfo.common.service.ConfigService;
import com.asiainfo.entity.User;
import com.asiainfo.exception.ActiveAccountException;
import com.asiainfo.exception.MD5Exception;
import com.asiainfo.exception.ObjectToMapException;
import com.asiainfo.exception.SaveUserException;
import com.asiainfo.exception.SendEmailException;
import com.asiainfo.sso.service.RedisSessionService;
import com.asiainfo.sso.service.UserService;
import com.asiainfo.util.ExceptionUtil;
import com.asiainfo.util.LoggerUtil;
import com.asiainfo.util.MD5Util;
import com.asiainfo.util.PageTemplate;
import com.asiainfo.util.WebResult;

@RestController
public class SsoController {
	@Resource
	private UserService userService;
	@Resource
	private BaseExceptionService baseExceptionService;
	@Resource
	private RedisSessionService redisSessionService;
	@Resource
	private ConfigService configService;
	
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
			this.userService.activeAccount(account);
			return PageTemplate.getActiveAccountPage(WebResult.ACTIVE_ACCOUNT_SUCCESS);
		} catch (ActiveAccountException e) {
			e.setMessage(ExceptionUtil.getExceptionMessage(e));
			baseExceptionService.saveBaseException(e);
			return PageTemplate.getActiveAccountPage(WebResult.ACTIVE_ACCOUNT_FAIL);

		}
		
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
		
		//登录成功，调用redis服务,将user实体的信息放入redis中
		try {
			String s = this.redisSessionService.beginSession(user);
			
			LoggerUtil.info(this.getClass(), s);
			return WebResult.LOGIN_SUCCESS;
			
		} catch (ObjectToMapException e) {
			this.baseExceptionService.saveBaseException(e);
			return WebResult.LOGIN_FAIL;
		}
		
		
	}
	
}
