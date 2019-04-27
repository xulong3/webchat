package com.asiainfo.message.dao;

import java.util.List;

import com.asiainfo.entity.FriendDynamic;

public interface FriendDynamicDao {
	int insertFriendDynamic(FriendDynamic friendDynamic);
	
	List<FriendDynamic> selectFriendDynamicByAccount(String account);
	
	int updateFriendDynamicPraiseCount(String dynamicId);
	
	int updateFriendDynamicPraiseCountMinus(String dynamicId);
	
}
