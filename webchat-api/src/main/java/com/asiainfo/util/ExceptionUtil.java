package com.asiainfo.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
	public static String getExceptionMessage(Exception e){
		StringWriter sw=new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		
		return sw.toString();
		
	}
	
	
}
