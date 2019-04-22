package com.asiainfo.common.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.common.service.FileService;
import com.asiainfo.util.MessageUtil;
import com.asiainfo.util.service.JdbcService;
import com.asiainfo.util.service.impl.JdbcServiceImpl;
import com.asiainfo.vo.MessageVo;

@Transactional
public class FileServiceImpl implements FileService{

	private JdbcService jdbcService=new JdbcServiceImpl();
	
	@Override
	public String createUserRootDir(String token, String timeStamp) {
		String value=null;
		try {
			value = jdbcService.queryConfigValueByKey("nginx_root");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		String path=value+token+"_"+timeStamp;
		
		File file = new File(path);
		boolean b = file.mkdir();
		if(b){
			
			return "yes";
		}else{
			return "no";
		}
	}

	@Override
	public void createP2pChatFile(String userAccount,String friendAccount) {
		String value=null;
		try {
			value = jdbcService.queryConfigValueByKey("nginx_root");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		String parentDirPath=value+"p2pchatfiles";
		
		File f = new File(parentDirPath);
		
		if(!f.exists()){
			f.mkdirs();
		}
		
		String fileName1=userAccount+"with"+friendAccount+".txt";
		String fileName2=friendAccount+"with"+userAccount+".txt";
		
		String filePath1=parentDirPath+"/"+fileName1;
		String filePath2=parentDirPath+"/"+fileName2;
		
		File f1 = new File(filePath1);
		File f2 = new File(filePath2);
		
		if(!f1.exists() && !f2.exists()){
			try {
				f1.createNewFile();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public MessageVo saveMessageToFile(String senderAccount, String receiverAccount, String message) {
		
		String value=null;
		try {
			value = jdbcService.queryConfigValueByKey("nginx_root");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		String fileName1=senderAccount+"with"+receiverAccount+".txt";
		String fileName2=receiverAccount+"with"+senderAccount+".txt";
		
		String path1=value+"p2pchatfiles/"+fileName1;
		String path2=value+"p2pchatfiles/"+fileName2;
		
		File f1 = new File(path1);
		File f2 = new File(path2);
		
		MessageVo msg = new MessageVo();
		msg.setSendTime(String.valueOf(new Date().getTime()));
		msg.setSender(senderAccount);
		msg.setMessage(message);
		if(f1.exists()){
			msg.setFilePath(path1);
			
			MessageUtil.append(msg);
			return msg;
		}
		
		if(f2.exists()){
			msg.setFilePath(path2);
			
			MessageUtil.append(msg);
			return msg;
		}
		
		return null;
	}

	@Override
	public void uploadUserPortrait(String relativePath,byte[] b,int len) {
		 
		try {
			String value = jdbcService.queryConfigValueByKey("nginx_root");
			
			String localPath=value+relativePath;
			
			
			OutputStream out = new FileOutputStream(localPath);
			
			out.write(b, 0, len);
			
			out.flush();
			
			out.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
}
