package com.asiainfo.label.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.asiainfo.entity.Label;
import com.asiainfo.entity.SysLabel;
import com.asiainfo.label.dao.LabelDao;
import com.asiainfo.label.service.LabelService;
import com.asiainfo.util.JsonUtil;
import com.asiainfo.util.LoggerUtil;
import com.asiainfo.util.RedisKey;
import com.asiainfo.vo.FriendItemVo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class LabelServiceImpl implements LabelService{

	@Resource
	private LabelDao labelDao;
	@Resource
	private JedisPool jedisPool;
	
	
	


	@Override
	public String loadSysLabelToRedis(String token) {
		SysLabel syslabel = this.labelDao.selectSysLabelByAccount(token);
		String s = JsonUtil.objectToJsonString(syslabel);
		Jedis jedis = jedisPool.getResource();
		Boolean b = jedis.exists(RedisKey.SYSLABEL_KEY);
		if(b){
			Long l = jedis.hset(RedisKey.SYSLABEL_KEY, RedisKey.SYSLABEL_HASH_KEY_PREFIX+token, s);
		}else{
			Map<String, String> map = new HashMap<String,String>();
			map.put(RedisKey.SYSLABEL_HASH_KEY_PREFIX+token, s);
			String res = jedis.hmset(RedisKey.SYSLABEL_KEY, map);
		}
		jedis.close();
		return "yes";
	}


	@Override
	public String loadLabelToRedis(String token) {
		Jedis jedis = jedisPool.getResource();
		String redisKey=RedisKey.LABEL_KEY_PREFIX+token;
		List<Label> list = this.labelDao.selectLabelByAccount(token);
		
		if(list.size()!=0){
			Map<String, String> map = new HashMap<String,String>();
			
			
			for (Label l : list) {
				LoggerUtil.info(this.getClass(), "----------"+l.getLabelKey());
				if(l.getLabelValue()==null){
					
					map.put(l.getLabelKey(), "");
				}else{
					map.put(l.getLabelKey(), l.getLabelValue());
				}
			}
			
			String res = jedis.hmset(redisKey, map);
			
			LoggerUtil.info(this.getClass(), "----------"+res);
		}
		
		jedis.close();
		return "yes";
			
		
		
	}


	@Override
	public String querySysLabelCache(String token) {
		Jedis jedis = jedisPool.getResource();
		List<String> list = jedis.hmget(RedisKey.SYSLABEL_KEY, RedisKey.SYSLABEL_HASH_KEY_PREFIX+token);
		jedis.close();
		return list.get(0);
	}


	@Override
	public String queryLabelCache(String token) {
		Jedis jedis = jedisPool.getResource();
		Map<String, String> map = jedis.hgetAll(RedisKey.LABEL_KEY_PREFIX+token);
		jedis.close();
		return JsonUtil.mapToJsonString(map);
	}


	@Override
	public List<SysLabel> querySysLabelPortrait(List<FriendItemVo> list) {
		
		return this.labelDao.selectSysLabelPortrait(list);
	}
	
	
	
}
