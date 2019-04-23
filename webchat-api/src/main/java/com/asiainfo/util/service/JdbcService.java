package com.asiainfo.util.service;

public interface JdbcService {

	String queryConfigValueByKey(String key) throws Exception;
	
	String modifyConfigLabel(String account,String configKey,String configValue,String javaType) throws Exception;
}
