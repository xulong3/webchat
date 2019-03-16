package com.asiainfo.sso.service;

import com.asiainfo.entity.User;

public interface RedisSessionService {
	/**
	 * 开始session
	 * @param user 放入session的user
	 * @return yes表示成功，no表示失败
	 */
	String beginSession(User user);
}
