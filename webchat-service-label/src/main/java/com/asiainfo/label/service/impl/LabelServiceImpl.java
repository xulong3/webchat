package com.asiainfo.label.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.entity.Label;
import com.asiainfo.entity.SysLabel;
import com.asiainfo.label.dao.LabelDao;
import com.asiainfo.label.service.LabelService;
import com.asiainfo.util.JsonUtil;
import com.asiainfo.util.LoggerUtil;
import com.asiainfo.util.RedisKey;
import com.asiainfo.vo.FriendItemVo;
import com.asiainfo.vo.SysLabelVo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Transactional
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
		if(!jedis.exists(RedisKey.SYSLABEL_KEY)){
			
		}
		if(!jedis.hexists(RedisKey.SYSLABEL_KEY, RedisKey.SYSLABEL_HASH_KEY_PREFIX+token)){
			//从数据库中加载
			SysLabel sysLabel = labelDao.selectSysLabelByAccount(token);
			//将sysLabel放入缓存
			jedis.hset(RedisKey.SYSLABEL_KEY, RedisKey.SYSLABEL_HASH_KEY_PREFIX+token, 
					JsonUtil.objectToJsonString(sysLabel));
			
			return JsonUtil.objectToJsonString(sysLabel);
			
		}
		
		List<String> list = jedis.hmget(RedisKey.SYSLABEL_KEY, RedisKey.SYSLABEL_HASH_KEY_PREFIX+token);
		jedis.close();
		return list.get(0);
	}


	@Override
	public String queryLabelCache(String token) {
		Jedis jedis = jedisPool.getResource();
		
		if(!jedis.exists(RedisKey.LABEL_KEY_PREFIX+token)){
			List<Label> labels = labelDao.selectLabelByAccount(token);
			if(labels==null || labels.size()==0){
				return "";
			}
			
			Map<String, String> map = new HashMap<String,String>();
			for (Label label : labels) {
				map.put(label.getLabelKey(), label.getLabelValue());
			}
			jedis.hmset(RedisKey.LABEL_KEY_PREFIX+token, map);
			jedis.close();
			return JsonUtil.mapToJsonString(map);
			
		}
		
		
		Map<String, String> map = jedis.hgetAll(RedisKey.LABEL_KEY_PREFIX+token);
		jedis.close();
		return JsonUtil.mapToJsonString(map);
	}


	@Override
	public List<SysLabel> querySysLabelPortrait(List<FriendItemVo> list) {
		
		return this.labelDao.selectSysLabelPortrait(list);
	}


	@Override
	public String modifySysLabelPortrait(SysLabel sysLabel) {
		int rows = this.labelDao.updateSysLabelPortrait(sysLabel);
		
		if(rows==1){
			
			return "yes";
		}else{
			throw new RuntimeException();
		}
		
	}
	
	@Override
	public String modifySysLabel(SysLabelVo sysLabelVo) {
		SysLabel sysLabel = new SysLabel();
		sysLabel.setRealname(sysLabelVo.getRealname());
		sysLabel.setEnglishName(sysLabelVo.getEnglishName());
		sysLabel.setSex(sysLabelVo.getSex());
		
		try {
			if(!"".equals(sysLabelVo.getBirthday())){
				
				sysLabel.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(sysLabelVo.getBirthday()));
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		sysLabel.setAge(Integer.parseInt(sysLabelVo.getAge().equals("")?"0":sysLabelVo.getAge()));
		
		sysLabel.setConstellation(sysLabelVo.getConstellation());
		
		sysLabel.setCompany(sysLabelVo.getCompany());
		sysLabel.setProfession(sysLabelVo.getProfession());
		sysLabel.setSchool(sysLabelVo.getSchool());
		sysLabel.setPhone(sysLabelVo.getPhone());
		sysLabel.setPresentAddress(sysLabelVo.getPresentAddress());
		sysLabel.setHometown(sysLabelVo.getHometown());
		sysLabel.setHobby(sysLabelVo.getHobby());
		sysLabel.setSignature(sysLabelVo.getSignature());
		sysLabel.setAccount(sysLabelVo.getAccount());
		
		
		int rows = this.labelDao.updateSysLabel(sysLabel);
		
		if(rows==1){
			
			return "yes";
		}else{
			throw new RuntimeException();
		}
		
	}


	@Override
	public String saveLabel(Label label) {
		int rows = this.labelDao.insertLabel(label);
		if(rows==1){
			
			return "yes";
		}else{
			throw new RuntimeException();
		}
		
		
	}


	@Override
	public String removeLabelByAccountAndKey(Label label) {
		int rows = this.labelDao.deleteLabelByAccountAndKey(label);
		if(rows==1){
			
			return "yes";
		}else{
			throw new RuntimeException();
		}
	}


	@Override
	public String modifyLabelValue(Label label) {
		int rows = this.labelDao.updateLabelValue(label);
		if(rows==1){
			
			return "yes";
		}else{
			throw new RuntimeException();
		}
	}
	
	
	
}
