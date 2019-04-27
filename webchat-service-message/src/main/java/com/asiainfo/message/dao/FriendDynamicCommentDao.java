package com.asiainfo.message.dao;

import java.util.List;

import com.asiainfo.entity.FriendDynamicComment;

public interface FriendDynamicCommentDao {
	int insertFriendDynamicComment(FriendDynamicComment friendDynamicComment);
	
	List<FriendDynamicComment> selectFriendDynamicCommentByDynamicId(String dynamicId);

}
