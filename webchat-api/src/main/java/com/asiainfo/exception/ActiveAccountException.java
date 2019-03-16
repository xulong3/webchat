package com.asiainfo.exception;

import com.asiainfo.entity.BaseBusinessException;
import com.asiainfo.util.ExceptionCode;
import com.asiainfo.util.ExceptionName;

public class ActiveAccountException extends BaseBusinessException {

	public ActiveAccountException(String account) {
		super(account);
		this.code=ExceptionCode.ACTIVE_ACCOUNT.getValue();
		this.name=ExceptionName.ACTIVE_ACCOUNT.getValue();
	}

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -5914985537838544301L;

	


}
