package com.asiainfo.message.service;

import java.util.List;
import java.util.Map;

import com.asiainfo.entity.FriendDynamic;

public interface FriendDynamicService {
	
	String saveFriendDynamic(FriendDynamic friendDynamic);

	List<FriendDynamic> queryFriendDynamicSelf(String account);
	
	List<FriendDynamic> queryFriendDynamicAll(List<Map<String,String>> list);
	
	String modifyFriendDynamicPraiseCount(String dynamicId);
	
	String modifyFriendDynamicPraiseCountMinus(String dynamicId);
}
