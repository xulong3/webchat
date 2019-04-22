package com.asiainfo.sso.service;

import java.util.Map;

import com.asiainfo.entity.User;

public interface SessionManager {
	//刷新auth 缓存
	String refreshAuthCache(User user);
	//根据token查看会话是否过期
	String isSessionExpire(String token);
	//查看用户登录状态
	int queryUserStatus(String token);
	//根据token获取整个用户的hash
	Map<String,String> queryUser(String token);
	//放入用户状态
	String saveUserStatus(String token,int value);
	
	String isIllegalLogin(String token);
	
	String hasSysLabelCache(String token);
	
	String hasLabelCache(String token);
	
	void bindStompSidWithAccount(String sid,String account);
	
	String getAccountBySid(String newSid);
	
	void modifyUserStatus(String account,int status);
	
	void clearCacheByKey(String key);
	
	void clearHashCacheByField(String key,String... fields);
}
