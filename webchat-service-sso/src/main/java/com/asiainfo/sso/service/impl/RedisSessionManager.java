package com.asiainfo.sso.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.asiainfo.entity.User;
import com.asiainfo.exception.ObjectToMapException;
import com.asiainfo.sso.service.SessionManager;
import com.asiainfo.util.ExceptionUtil;
import com.asiainfo.util.JsonUtil;
import com.asiainfo.util.LoggerUtil;
import com.asiainfo.util.RedisKey;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisSessionManager implements SessionManager{

	@Resource
	private JedisPool jedisPool;
	
	@Override
	public String refreshAuthCache(User user) {
		Jedis jedis = jedisPool.getResource();
		String redisKey=RedisKey.TOKEN_KEY_PREFIX+user.getAccount();
		
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
		jedis.close();
		return "yes";
	}

	@Override
	public String isSessionExpire(String token) {
		
		String redisKey=RedisKey.TOKEN_KEY_PREFIX+token;
		Jedis jedis = jedisPool.getResource();
		Boolean b = jedis.exists(redisKey);
		jedis.close();
		if(b){
			Boolean b2 = jedis.exists(RedisKey.STATUS_KEY);
			if(b2){
				Boolean b3 = jedis.hexists(RedisKey.STATUS_KEY, RedisKey.STATUS_HASH_KEY_PREFIX+token);
				if(b3){
					return "no";
				}else{
					return "yes";
				}
			}else{
				return "yes";
			}
			
		}else{
			
			return "yes";
		}
	}

	@Override
	public int queryUserStatus(String token) {
		Jedis jedis = jedisPool.getResource();
		Boolean b = jedis.exists(RedisKey.STATUS_KEY);
		
		if(b){
			Boolean b1 = jedis.hexists(RedisKey.STATUS_KEY, RedisKey.STATUS_HASH_KEY_PREFIX+token);
			if(b1){
				List<String> list = jedis.hmget(RedisKey.STATUS_KEY, RedisKey.STATUS_HASH_KEY_PREFIX+token);
				Integer i = Integer.valueOf(list.get(0));
				jedis.close();
				return i;
			
			}else{
				jedis.close();
				return 0;
			}
		}else{
			jedis.close();
			return 0;
		}
		
		
		
	}

	@Override
	public Map<String, String> queryUser(String token) {
		String redisKey=RedisKey.TOKEN_KEY_PREFIX+token;
		Jedis jedis = jedisPool.getResource();
		Map<String, String> user = jedis.hgetAll(redisKey);
		jedis.close();
		return user;
	}

	@Override
	public String saveUserStatus(String token,int value) {
		Jedis jedis = jedisPool.getResource();
		//
		Boolean b = jedis.exists(RedisKey.STATUS_KEY);
		if(b){
			Long l = jedis.hset(RedisKey.STATUS_KEY, RedisKey.STATUS_HASH_KEY_PREFIX+token, String.valueOf(value));
			LoggerUtil.info(this.getClass(), String.valueOf(l));
		}else{
			Map<String,String> map=new HashMap<String,String>();
			map.put(RedisKey.STATUS_HASH_KEY_PREFIX+token, String.valueOf(value));
			String s = jedis.hmset(RedisKey.STATUS_KEY, map);
			LoggerUtil.info(this.getClass(), s);
		}
		
		
		jedis.close();
		return "yes";
	}

	@Override
	public String isIllegalLogin(String token) {
		Jedis jedis = jedisPool.getResource();
		
		List<String> list = jedis.hmget(RedisKey.STATUS_KEY, RedisKey.STATUS_HASH_KEY_PREFIX+token);
		Integer i = Integer.valueOf(list.get(0));
		if(i==0){
			jedis.close();
			return "yes";
			
		}else if(i==1){
			jedis.close();
			return "no";
		}
		jedis.close();
		return null;
		
	}

	@Override
	public String hasSysLabelCache(String token) {
		Jedis jedis = jedisPool.getResource();
		Boolean b = jedis.exists(RedisKey.SYSLABEL_KEY);
		if(b){
			Boolean b2 = jedis.hexists(RedisKey.SYSLABEL_KEY, RedisKey.SYSLABEL_HASH_KEY_PREFIX+token);
			if(b2){
				jedis.close();
				return "yes";
			}else{
				jedis.close();
				return "no";
			}
		}else{
			jedis.close();
			return "no";
		}
		
		
		
	}

	@Override
	public String hasLabelCache(String token) {
		Jedis jedis = jedisPool.getResource();
		Boolean b = jedis.exists(RedisKey.LABEL_KEY_PREFIX+token);
		if(b){
			jedis.close();
			return "yes";
		}else{
			jedis.close();
			return "no";
		}
		
		
	}

	@Override
	public void bindStompSidWithAccount(String sid, String account) {
		Jedis jedis = jedisPool.getResource();
		
		
		if(!jedis.exists(RedisKey.ALL_USER_STOMP_SID)){
			
			Map<String, String> map = new HashMap<String,String>();
			Map<String, String> param = new HashMap<String,String>();
			param.put("oid_sid", sid);
			param.put("new_sid", sid);
			map.put(account, JsonUtil.mapToJsonString(param));
			
			jedis.hmset(RedisKey.ALL_USER_STOMP_SID, map);
			
		}else{
			if(jedis.hexists(RedisKey.ALL_USER_STOMP_SID, account)){
				
				List<String> values = jedis.hmget(RedisKey.ALL_USER_STOMP_SID, account);
				Map<String, String> param = JsonUtil.jsonStringToMap(values.get(0));
				param.put("oid_sid", param.get("new_sid"));
				param.put("new_sid", sid);
				
				jedis.hset(RedisKey.ALL_USER_STOMP_SID, account, JsonUtil.mapToJsonString(param));
			}else{
				
				Map<String, String> param = new HashMap<String,String>();
				param.put("old_sid", sid);
				param.put("new_sid", sid);
				
				jedis.hset(RedisKey.ALL_USER_STOMP_SID, account, JsonUtil.mapToJsonString(param));
			}
		}
		jedis.close();
	}

	@Override
	public String getAccountBySid(String newSid) {
		String account=null;
		
		Jedis jedis = jedisPool.getResource();
		Map<String, String> map = jedis.hgetAll(RedisKey.ALL_USER_STOMP_SID);
		Set<String> set = map.keySet();
		for (String key : set) {
			Map<String, String> param = JsonUtil.jsonStringToMap(map.get(key));
			if(newSid.equals(param.get("new_sid"))){
				account=key;
				break;
			}
		}
		jedis.close();
		return account;
	}

	@Override
	public void modifyUserStatus(String account, int status) {
		Jedis jedis = jedisPool.getResource();
		jedis.hset(RedisKey.STATUS_KEY, RedisKey.STATUS_HASH_KEY_PREFIX+account, status+"");
		
		
		
		
		jedis.close();
		
	}

}
