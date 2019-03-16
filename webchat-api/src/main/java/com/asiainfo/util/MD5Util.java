package com.asiainfo.util;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

public class MD5Util {
	
    public static String encrypt(String str) throws Exception{
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
		
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		
        return newstr;
    }
}
