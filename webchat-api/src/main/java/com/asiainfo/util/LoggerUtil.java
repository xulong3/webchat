package com.asiainfo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
	
	
	public static void info(Class<?> c,String msg){
		Logger logger = LoggerFactory.getLogger(c);
		logger.info(msg);
		
		
		
	}
	public static void error(Class<?> c,String msg){
		Logger logger = LoggerFactory.getLogger(c);
		logger.error(msg);
		
		
		
	}
	public static void debug(Class<?> c,String msg){
		Logger logger = LoggerFactory.getLogger(c);
		logger.debug(msg);
		
		
		
	}
	public static void warn(Class<?> c,String msg){
		Logger logger = LoggerFactory.getLogger(c);
		logger.warn(msg);
		
		
		
	}
}
