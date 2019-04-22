package com.asiainfo.common.service;

import com.asiainfo.entity.User;
import com.asiainfo.vo.MessageVo;

public interface FileService {
	String createUserRootDir(String token,String timeStamp);
	void createP2pChatFile(String userAccount,String friendAccount);
	MessageVo saveMessageToFile(String senderAccount,String receiverAccount,String message);
	
	void uploadUserPortrait(User user,String fileName,byte[] b,int len);
}
