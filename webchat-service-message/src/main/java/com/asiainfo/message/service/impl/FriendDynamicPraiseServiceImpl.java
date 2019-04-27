package com.asiainfo.message.service.impl;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.entity.FriendDynamicPraise;
import com.asiainfo.message.dao.FriendDynamicPraiseDao;
import com.asiainfo.message.service.FriendDynamicPraiseService;


@Transactional
public class FriendDynamicPraiseServiceImpl implements FriendDynamicPraiseService{

	@Resource
	private FriendDynamicPraiseDao friendDynamicPraiseDao;

	@Override
	public String saveFriendDynamicPraise(FriendDynamicPraise friendDynamicPraise) {
		int rows = this.friendDynamicPraiseDao.insertFriendDynamicPraise(friendDynamicPraise);
		return "yes";
	}

	@Override
	public int queryFriendDynamicPraiseCount(FriendDynamicPraise friendDynamicPraise) {
		
		return this.friendDynamicPraiseDao.selectFriendDynamicPraiseCount(friendDynamicPraise);
	}

	@Override
	public String deleteFriendDynamicPraise(FriendDynamicPraise friendDynamicPraise) {
		int rows = this.friendDynamicPraiseDao.deleteFriendDynamicPraise(friendDynamicPraise);
		return "yes";
	}

	

	
}
