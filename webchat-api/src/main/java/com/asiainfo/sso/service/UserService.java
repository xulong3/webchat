package com.asiainfo.sso.service;

import java.util.Map;

import com.asiainfo.entity.User;

public interface UserService {
	//增加用户，成功，返回账号，失败，抛出异常
	String saveUser(User user);
	
	//获取新账号
	String getNewAccount();
	
	//发送激活账号的邮件，发送成功，返回yes，失败，抛出异常
	String sendActiveAccountEmail(User user);
	
	//激活账号，成功返回yes，失败抛出异常
	String activeAccount(String account);
	
	
	
	//通过邮箱查询user的数量
	int queryUserCountByEmail(String email);
	
	//用户是否可用,返回yes no
	String isUserAvailable(String account);
	
	//根据账号或email查询用户
	User queryUserByToken(Map<String,String> map);
	
	User queryUserByAccount(String account);
}
