package com.asiainfo.message.service.impl;

import javax.annotation.Resource;

import com.asiainfo.entity.Friend;
import com.asiainfo.message.dao.FriendDao;
import com.asiainfo.message.service.FriendService;

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
	
}
