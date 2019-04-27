package com.asiainfo.message.service;

import com.asiainfo.entity.ChatList;

public interface ChatListService {
	
	String saveOrUpdateChatList(ChatList chatList);
	ChatList selectChatListByAccount(String account);
	
}
