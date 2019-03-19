package com.asiainfo.label.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.asiainfo.entity.Label;
import com.asiainfo.label.dao.LabelDao;
import com.asiainfo.label.service.LabelService;
import com.asiainfo.util.JsonUtil;
import com.asiainfo.util.LoggerUtil;
import com.asiainfo.util.RedisKey;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class LabelServiceImpl implements LabelService{

	@Resource
	private LabelDao labelDao;
	@Resource
	private JedisPool jedisPool;
	
	
	@Override
	public String queryLabel(String token) {
		Jedis jedis = jedisPool.getResource();
		String redisKey=RedisKey.LABEL_KEY_PREFIX+token;
		Boolean b = jedis.exists(redisKey);
		if(b){
			Map<String, String> map = jedis.hgetAll(redisKey);
			String jsonString = JsonUtil.mapToJsonString(map);
			jedis.close();
			return jsonString;
		}else{
			List<Label> list = this.labelDao.selectLabelByToken(token);
			
			
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
			jedis.close();
			return JsonUtil.mapToJsonString(map);
			
		}
		
	}
	
	
	
}
