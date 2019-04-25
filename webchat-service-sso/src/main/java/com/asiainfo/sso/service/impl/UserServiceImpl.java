package com.asiainfo.sso.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.entity.ConfigLabel;
import com.asiainfo.entity.User;
import com.asiainfo.exception.ActiveAccountException;
import com.asiainfo.exception.MD5Exception;
import com.asiainfo.exception.SaveUserException;
import com.asiainfo.exception.SendEmailException;
import com.asiainfo.sso.dao.ConfigLabelDao;
import com.asiainfo.sso.dao.SysLabelDao;
import com.asiainfo.sso.dao.UserDao;
import com.asiainfo.sso.service.UserService;
import com.asiainfo.util.EmailUtil;
import com.asiainfo.util.ExceptionUtil;
import com.asiainfo.util.MD5Util;
import com.asiainfo.vo.FriendItemVo;

public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	@Resource
	private SysLabelDao sysLabelDao;
	@Resource
	private ConfigLabelDao configLabelDao;
	
	
	@Override
	@Transactional
	public String saveUser(User user) {
		//对密码明文进行加密
		String encrypt=null;
		try {
			encrypt = MD5Util.encrypt(user.getPassword());
		} catch (Exception e) {
			throw new MD5Exception(null, ExceptionUtil.getExceptionMessage(e));
		}
		user.setPassword(encrypt);
		String newAccount = getNewAccount();
		user.setAccount(newAccount);
		user.setAvailable(0);
		user.setRegTime(new Date());
		
		int rows = this.userDao.insertUser(user);
		
		
		
		if(rows==1){
			
			return newAccount;
		}else{
			throw new SaveUserException(newAccount);
		}
		
		
		
		
	}

	@Override
	public synchronized String getNewAccount() {
		//如果用户数为空，就为0
		int maxId = this.userDao.selectUserMaxId();
		
		
		return (100001+maxId)+"";
	}

	@Override
	public String sendActiveAccountEmail(User user) {
		String title="账号激活";
		String receiver=user.getEmail();
		
		String content=EmailUtil.getActiveAccountTemplate(user.getAccount());
		
		try {
			EmailUtil.sendSimpleMail(title, receiver, content);
			return "yes";
		} catch (Exception e) {
			throw new SendEmailException(user.getAccount(), ExceptionUtil.getExceptionMessage(e));
		}
	}

	@Override
	@Transactional
	public String activeAccount(String account) {
		User user = new User();
		user.setAccount(account);
		Date actTime = new Date();
		user.setActTime(actTime);
		user.setAvailable(1);
		int rows1 = this.userDao.updateUserActTime(user);
		int rows2 = this.userDao.updateUserAvailable(user);
		//激活用户之后，还要创造用户默认的系统标签
		//获取系统标签
		int rows3 = this.sysLabelDao.insertSysLabel(account);
		//创建用户默认的配置标签
		ConfigLabel cl = new ConfigLabel();
		cl.setAccount(account);
		cl.setValidateWay(1);
		cl.setFriendCircle(1);
		int rows4 = this.configLabelDao.insertConfigLabel(cl);
		
		if(rows1==1 && rows2==1 && rows3==1 && rows4==1){
			
			
			return new SimpleDateFormat("yyyyMMdd").format(actTime);
		}else{
			throw new ActiveAccountException(account);
		}
		
	}
	
	


	@Override
	public int queryUserCountByEmail(String email) {
		int count = this.userDao.selectUserCountByEmail(email);
		
		return count;
	}

	@Override
	public String isUserAvailable(String account) {
		int available = this.userDao.selectUserAvailable(account);
		if(available==1){
			return "yes";
		}else{
			
			return "no";
		}
	}

	@Override
	public User queryUserByToken(Map<String, String> map) {
		User user = this.userDao.selectUserByToken(map);
		return user;
	}

	@Override
	public User queryUserByAccount(String account) {
		return this.userDao.selectUserByAccount(account);
	}

	@Override
	public List<User> queryUserBySomeAccount(List<FriendItemVo> list) {
		
		return this.userDao.selectUserBySomeAccount(list);
	}

	@Override
	public String modifyUserNickname(User user) {
		int rows = this.userDao.updateUserNickname(user);
		
		if(rows==1){
			
			return "yes";
		}else{
			throw new RuntimeException();
		}
	}
	
	@Override
	public String modifyUserEmail(User user) {
		int rows = this.userDao.updateUserEmail(user);
		
		if(rows==1){
			
			return "yes";
		}else{
			throw new RuntimeException();
		}
	}
	
	@Override
	public String modifyUserPwd(User user) {
		int rows = this.userDao.updateUserPwd(user);
		
		if(rows==1){
			
			return "yes";
		}else{
			throw new RuntimeException();
		}
	}

}
