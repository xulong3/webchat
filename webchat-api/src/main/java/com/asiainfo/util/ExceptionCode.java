package com.asiainfo.util;

public enum ExceptionCode {
	ACTIVE_ACCOUNT(101),MD5(102),SAVE_USER(103),SEND_EMAIL(105),
	OBJECT_TO_MAP(106);
	
	private int value;
	private ExceptionCode(int value){
		this.value=value;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
}
