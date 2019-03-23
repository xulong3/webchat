package com.asiainfo.label.service;

public interface LabelService {

	
	String loadSysLabelToRedis(String token);
	String loadLabelToRedis(String token);
	String querySysLabelCache(String token);
	String queryLabelCache(String token);
}
