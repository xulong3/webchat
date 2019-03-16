package com.asiainfo.common.dao;

import com.asiainfo.entity.Config;

public interface ConfigDao {
	
	Config selectValueByKey(String key);

}
