package com.asiainfo.message.service;

import com.asiainfo.entity.FriendDynamicPraise;

public interface FriendDynamicPraiseService {
	
	String saveFriendDynamicPraise(FriendDynamicPraise friendDynamicPraise);
	
	int queryFriendDynamicPraiseCount(FriendDynamicPraise friendDynamicPraise);
	
	String deleteFriendDynamicPraise(FriendDynamicPraise friendDynamicPraise);
}
