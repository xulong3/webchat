package com.asiainfo.exception;

import com.asiainfo.entity.BaseException;
import com.asiainfo.util.ExceptionCode;
import com.asiainfo.util.ExceptionName;

public class MD5Exception extends BaseException {

	public MD5Exception(String account,String message) {
		super(account,message);
		this.code=ExceptionCode.MD5.getValue();
		this.name=ExceptionName.MD5.getValue();
	}

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -7172862723186858254L;

	
	
}
