package com.asiainfo.exception;

import com.asiainfo.entity.BaseBusinessException;
import com.asiainfo.util.ExceptionCode;
import com.asiainfo.util.ExceptionName;

public class SaveUserException extends BaseBusinessException {

	

	public SaveUserException(String account) {
		super(account);
		this.code=ExceptionCode.SAVE_USER.getValue();
		this.name=ExceptionName.SAVE_USER.getValue();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8872262185377811271L;



}
