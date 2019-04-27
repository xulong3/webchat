package com.asiainfo.message.service.impl;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.entity.ChatList;
import com.asiainfo.message.dao.ChatListDao;
import com.asiainfo.message.service.ChatListService;


@Transactional
public class ChatListServiceImpl implements ChatListService{

	@Resource
	private ChatListDao chatListDao;
	
	@Override
	public String saveOrUpdateChatList(ChatList chatList) {
		int count = this.chatListDao.selectChatListCountByAccount(chatList.getAccount());
		int rows=0;
		if(count==0){
			rows = this.chatListDao.insertChatList(chatList);
		}else{
			rows = this.chatListDao.updateChatList(chatList);
		}
		
		return "yes";
	}

	@Override
	public ChatList selectChatListByAccount(String account) {
		
		return this.chatListDao.selectChatListByAccount(account);
	}

	
	
	
}
