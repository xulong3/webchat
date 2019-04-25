package com.asiainfo.message.dao;

import java.util.List;

import com.asiainfo.entity.Friend;

public interface FriendDao {
	int insertFriend(Friend friend);
	List<Friend> selectFriendByTwoAccount(Friend friend);
	List<Friend> selectFriendByUserAccount(Friend friend);
	List<Friend> selectFriendByFriendAccount(Friend friend);
	int updateFriendAddStatusById(Friend friend);
	List<Friend> selectFriendByAddStatusAndTwoAccount(Friend friend);
	
	int deleteFriend(Friend friend);
}
