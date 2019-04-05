package com.asiainfo.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.entity.Friend;
import com.asiainfo.message.dao.FriendDao;
import com.asiainfo.message.service.FriendService;


@Transactional
public class FriendServiceImpl implements FriendService{

	@Resource
	private FriendDao friendDao;
	@Override
	public String saveFriendApply(Friend friend) {
		int rows = this.friendDao.insertFriend(friend);
		if(rows==1){
			return "yes";
		}else{
			throw new RuntimeException();
		}
	}
	@Override
	public Friend getFriendByTwoAccount(Friend friend) {
		List<Friend> friends = this.friendDao.selectFriendByTwoAccount(friend);
		if(friends.size()==0){
			return null;
		}  
		
		if(friends.size()==1){
			return friends.get(0);
		}
		
		return null;
	}
	@Override
	public List<Friend> queryFriendByUserAccount(Friend friend) {
		
		return this.friendDao.selectFriendByUserAccount(friend);
	}
	@Override
	public List<Friend> queryFriendByFriendAccount(Friend friend) {
		return this.friendDao.selectFriendByFriendAccount(friend);
	}
	@Override
	public String modifyFriendAddStatusById(Friend friend) {
		int rows = this.friendDao.updateFriendAddStatusById(friend);
		
		if(rows==1){
			
			return "yes";
		}else{
			throw new RuntimeException();
		}
	}
	@Override
	public List<Friend> queryFriend(Friend friend) {
		
		return this.friendDao.selectFriendByAddStatusAndTwoAccount(friend);
	}
	
}
