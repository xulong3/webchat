package com.asiainfo.util;

public enum ExceptionName {
	ACTIVE_ACCOUNT("激活账号异常"),MD5("md5异常"),SAVE_USER("增加用户异常"),SEND_EMAIL("发送邮件异常"),
	OBJECT_TO_MAP("对象转map异常");
	
	private String value;
	private ExceptionName(String value){
		this.value=value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
