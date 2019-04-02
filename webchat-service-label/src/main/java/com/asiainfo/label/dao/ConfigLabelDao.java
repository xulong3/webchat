package com.asiainfo.label.dao;

import com.asiainfo.entity.ConfigLabel;

public interface ConfigLabelDao {
	
	ConfigLabel selectValidateWayByAccount(String account);
	
}
