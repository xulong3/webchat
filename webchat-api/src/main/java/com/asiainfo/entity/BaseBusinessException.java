package com.asiainfo.entity;

public abstract class BaseBusinessException extends BaseException{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3303957584960059534L;

	
	protected BaseBusinessException(String account){
		super(account,null);
	}
	
}
