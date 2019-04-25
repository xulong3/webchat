package com.asiainfo.message.service;

import java.util.List;

import com.asiainfo.entity.Friend;

public interface FriendService {
	
	String saveFriendApply(Friend friend);
	
	Friend getFriendByTwoAccount(Friend friend);
	
	List<Friend> queryFriendByUserAccount(Friend friend);
	
	List<Friend> queryFriendByFriendAccount(Friend friend);
	
	String modifyFriendAddStatusById(Friend friend);
	
	List<Friend> queryFriend(Friend friend);
	
	String removeFriend(Friend friend);
	
	

}
