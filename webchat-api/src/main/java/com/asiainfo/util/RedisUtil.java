package com.asiainfo.util;

import java.util.Map;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	/**
	 * 检查某个key是否存在
	 * @param jedis
	 * @param key
	 * @return
	 */
	public static boolean containsKey(Jedis jedis,String key){
		Boolean b = jedis.exists(key);
		return b;
	}
	
	/**
	 * 放入一个hash
	 * @param jedis
	 * @param key
	 * @param map
	 * @return
	 */
	public static String addHash(Jedis jedis,String key,Map<String,String> map){
		return jedis.hmset(key, map);
		
	}
	
	/**
	 * 根据key删除数据
	 * @param jedis
	 * @param key
	 * @return
	 */
	public static long delKey(Jedis jedis,String key){
		Long del = jedis.del(key);
		return del;
	}
	
}
