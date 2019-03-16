package com.asiainfo.common.service;

import com.asiainfo.entity.BaseException;

public interface BaseExceptionService {
	/**
	 * 增加一条基础异常
	 * @param baseException 被增加的基础异常
	 * @return yes or no or 抛出异常
	 */
	String saveBaseException(BaseException baseException);
}
