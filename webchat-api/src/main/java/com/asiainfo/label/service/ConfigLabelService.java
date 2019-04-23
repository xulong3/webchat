package com.asiainfo.label.service;

import com.asiainfo.entity.ConfigLabel;

public interface ConfigLabelService {

	
	String queryValidateWayByAccount(String account);
	
	ConfigLabel queryConfigLabel(String account);
}
