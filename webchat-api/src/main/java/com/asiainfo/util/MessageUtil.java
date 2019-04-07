package com.asiainfo.util;

import java.io.FileOutputStream;

import com.asiainfo.vo.MessageVo;

public class MessageUtil {
	
	public static void append(MessageVo messageVO){
		
		try {
			FileOutputStream out = new FileOutputStream(messageVO.getFilePath(),true);
			out.write((messageVO.getSendTime()+"\n").getBytes("UTF-8"));
			out.write((messageVO.getSender()+"\n").getBytes("UTF-8"));
			out.write((messageVO.getMessage()+"\n").getBytes("UTF-8"));
			out.write("end".getBytes("UTF-8"));
			out.flush();
			out.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
}
