package com.asiainfo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
	
	
	public static void info(Class<?> c,String msg){
		Logger logger = LoggerFactory.getLogger(c);
		logger.info(msg);
		
		
		
	}
	
	
}
