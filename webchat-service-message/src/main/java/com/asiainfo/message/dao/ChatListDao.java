package com.asiainfo.message.dao;

import com.asiainfo.entity.ChatList;

public interface ChatListDao {
	int insertChatList(ChatList chatList);
	int updateChatList(ChatList chatList);
	int selectChatListCountByAccount(String account);
	
	ChatList selectChatListByAccount(String account);
}
