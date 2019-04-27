package com.asiainfo.label.service.impl;

import javax.annotation.Resource;

import com.asiainfo.entity.ConfigLabel;
import com.asiainfo.label.dao.ConfigLabelDao;
import com.asiainfo.label.service.ConfigLabelService;

public class ConfigLabelServiceImpl implements ConfigLabelService{

	@Resource
	private ConfigLabelDao configLabelDao;
	@Override
	public String queryValidateWayByAccount(String account) {
		ConfigLabel cl = this.configLabelDao.selectConfigLabelByAccount(account);
		if(cl==null){
			return "-1";
		}
		return cl.getValidateWay()+"";
	}
	@Override
	public ConfigLabel queryConfigLabel(String account) {
		
		return this.configLabelDao.selectConfigLabelByAccount(account);
	}
	@Override
	public String queryFriendCircleByAccount(String account) {
		ConfigLabel cl = this.configLabelDao.selectConfigLabelByAccount(account);
		if(cl==null){
			return "-1";
		}
		return cl.getFriendCircle()+"";
		
	}

	
	
	
	


	
	
}
