package com.asiainfo.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.entity.FriendDynamicComment;
import com.asiainfo.message.dao.FriendDynamicCommentDao;
import com.asiainfo.message.service.FriendDynamicCommentService;


@Transactional
public class FriendDynamicCommentServiceImpl implements FriendDynamicCommentService{

	@Resource
	private FriendDynamicCommentDao friendDynamicCommentDao;

	@Override
	public String saveFriendDynamicComment(FriendDynamicComment friendDynamicComment) {
		
		int rows = this.friendDynamicCommentDao.insertFriendDynamicComment(friendDynamicComment);
		return "yes";
	}

	@Override
	public List<FriendDynamicComment> queryFriendDynamicCommentByDynamicId(String dynamicId) {
		
		return this.friendDynamicCommentDao.selectFriendDynamicCommentByDynamicId(dynamicId);
	}

	

	
}
