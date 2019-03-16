package com.asiainfo.common.service.impl;

import javax.annotation.Resource;

import com.asiainfo.common.dao.ConfigDao;
import com.asiainfo.common.service.ConfigService;
import com.asiainfo.entity.Config;

public class ConfigServiceImpl implements ConfigService{

	@Resource
	private ConfigDao configDao;
	
	
	@Override
	public Config queryValueByKey(String key) {
		return this.configDao.selectValueByKey(key);
	}

}
