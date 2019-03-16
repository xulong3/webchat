package com.asiainfo.sso.service;

import com.asiainfo.entity.User;

public interface RedisSessionService {
	//开启session
	String beginSession(User user);
	//根据token查看会话是否过期
	String isSessionExpire(String token);
	//查看用户登录状态
	int queryUserStatus(String token);
}
