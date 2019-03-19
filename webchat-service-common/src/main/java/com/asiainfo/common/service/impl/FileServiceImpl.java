package com.asiainfo.common.service.impl;

import java.io.File;

import com.asiainfo.common.service.FileService;
import com.asiainfo.util.service.JdbcService;
import com.asiainfo.util.service.impl.JdbcServiceImpl;

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
		String path=value+token+timeStamp;
		
		File file = new File(path);
		boolean b = file.mkdir();
		if(b){
			
			return "yes";
		}else{
			return "no";
		}
	}
	
	

}
