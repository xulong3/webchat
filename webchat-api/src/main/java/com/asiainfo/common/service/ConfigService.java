package com.asiainfo.common.service;

import com.asiainfo.entity.Config;

public interface ConfigService {

	Config queryValueByKey(String key);
}
