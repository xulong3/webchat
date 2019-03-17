package com.asiainfo.util;

public class PageTemplate {
	public static String getActiveAccountPage(String message){
		
		
		return "<!DOCTYPE html><html><head><meta charset='UTF-8'><title>账号激活结果</title></head>"
				+ "<body><script type='text/javascript'>alert('"+message+"');</script></body></html>";
	}
	
	
	public static String getAccessForbiddenPage(String message){
		
		
		return "<!DOCTYPE html><html><head><meta charset='UTF-8'><title>禁止访问</title></head>"
				+ "<body><script type='text/javascript'>alert('"+message+"');</script></body></html>";
	}
}
