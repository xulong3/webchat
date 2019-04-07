package com.asiainfo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.common.service.FileService;
import com.asiainfo.util.JsonUtil;
import com.asiainfo.util.LoggerUtil;
import com.asiainfo.util.WebResult;
import com.asiainfo.vo.MessageVo;

@RestController
public class FileController {
	
	@Resource
	private FileService fileService;

	@RequestMapping("/uploadUserRootDir")
	public String uploadUserRootDir(String token,String timeStamp){
		LoggerUtil.info(this.getClass(), "----------"+token);
		LoggerUtil.info(this.getClass(), "----------"+timeStamp);
		
		String res = fileService.createUserRootDir(token, timeStamp);
		if("yes".equals(res)){
			return WebResult.INIT_USER_ROOT_SUCCESS;
		}else{
			return WebResult.INIT_USER_ROOT_FAIL;
		}
	}
	
	@RequestMapping("/createP2pChatFile")
	public void createP2pChatFile(String userAccount,String friendAccount){
		
		
		this.fileService.createP2pChatFile(userAccount, friendAccount);
	}
	
	@RequestMapping("/saveMessage")
	public String saveMessage(String userAccount,String friendAccount,String message){
		
		
		MessageVo msg = this.fileService.saveMessageToFile(userAccount, friendAccount, message);
		return JsonUtil.objectToJsonString(msg);
	}
}
