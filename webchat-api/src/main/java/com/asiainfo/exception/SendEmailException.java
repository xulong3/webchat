package com.asiainfo.exception;

import com.asiainfo.entity.BaseException;
import com.asiainfo.util.ExceptionCode;
import com.asiainfo.util.ExceptionName;

public class SendEmailException extends BaseException {

	public SendEmailException(String account,String message) {
		super(account,message);
		this.name=ExceptionName.SEND_EMAIL.getValue();
		this.code=ExceptionCode.SEND_EMAIL.getValue();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3171759763486320685L;

	
	
	
}
