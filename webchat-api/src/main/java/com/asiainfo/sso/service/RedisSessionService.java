package com.asiainfo.sso.service;

import com.asiainfo.entity.User;

public interface RedisSessionService {
	//开启session
	String beginSession(User user);
}
