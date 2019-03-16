package com.asiainfo.exception;

import com.asiainfo.entity.BaseException;
import com.asiainfo.util.ExceptionCode;
import com.asiainfo.util.ExceptionName;

public class ObjectToMapException extends BaseException{

	public ObjectToMapException(String account,String message) {
		super(account,message);
		this.code=ExceptionCode.OBJECT_TO_MAP.getValue();
		this.name=ExceptionName.OBJECT_TO_MAP.getValue();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4613807012717196294L;

	
	
}
