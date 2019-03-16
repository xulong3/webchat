package com.asiainfo.sso.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.asiainfo.entity.User;
import com.asiainfo.exception.ObjectToMapException;
import com.asiainfo.sso.service.RedisSessionService;
import com.asiainfo.util.ExceptionUtil;
import com.asiainfo.util.JsonUtil;
import com.asiainfo.util.LoggerUtil;
import com.asiainfo.util.RedisKey;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisSessionServiceImpl implements RedisSessionService{

	@Resource
	private JedisPool jedisPool;
	
	@Override
	public String beginSession(User user) {
		Jedis jedis = jedisPool.getResource();
		String redisKey=RedisKey.USER_INFO_prefix+user.getAccount();
		if(jedis.exists(redisKey)){
			//如果已经存在这个key，尽管这个key的登录状态是已登录，也无所谓，照样登录，登录一万次都可以
		}
		//将用户信息放入缓存
		Map<String, String> userMap=null;
		try {
			userMap = JsonUtil.objectToMap(user);
		} catch (Exception e) {
			throw new ObjectToMapException(user.getAccount(), ExceptionUtil.getExceptionMessage(e));
		}
		//成功返回ok
		String res = jedis.hmset(redisKey, userMap);
		LoggerUtil.info(this.getClass(), "----------"+res);
		return "yes";
	}

}
