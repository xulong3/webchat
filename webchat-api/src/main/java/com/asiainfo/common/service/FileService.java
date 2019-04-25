package com.asiainfo.common.service;

import java.util.List;

import com.asiainfo.vo.MessageVo;

public interface FileService {
	String createUserRootDir(String token,String date);
	void createP2pChatFile(String userAccount,String friendAccount);
	MessageVo saveMessageToFile(MessageVo messageVo);
	
	void uploadUserPortrait(String relativePath,byte[] b,int len);
	
	List<MessageVo> getAllMessage(MessageVo messageVo);
}
