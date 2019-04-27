package com.asiainfo.message.dao;

import com.asiainfo.entity.FriendDynamicPraise;

public interface FriendDynamicPraiseDao {
	int insertFriendDynamicPraise(FriendDynamicPraise friendDynamicPraise);
	
	int selectFriendDynamicPraiseCount(FriendDynamicPraise friendDynamicPraise);
	
	int deleteFriendDynamicPraise(FriendDynamicPraise friendDynamicPraise);
}
