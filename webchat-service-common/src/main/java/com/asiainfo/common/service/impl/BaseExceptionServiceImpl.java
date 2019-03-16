package com.asiainfo.common.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import com.asiainfo.common.dao.BaseExceptionDao;
import com.asiainfo.common.service.BaseExceptionService;
import com.asiainfo.entity.BaseException;
import com.asiainfo.util.LoggerUtil;

public class BaseExceptionServiceImpl implements BaseExceptionService{

	@Resource
	private BaseExceptionDao baseExceptionDao;
	
	@Override
	public String saveBaseException(BaseException baseException) {
		baseException.setTime(new Date());
		int rows = this.baseExceptionDao.insertBaseException(baseException);
		if(rows!=1){
			LoggerUtil.info(this.getClass(), "----------插入异常失败");
		}
		return "yes";
	}

}
