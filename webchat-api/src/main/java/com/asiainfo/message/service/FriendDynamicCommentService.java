package com.asiainfo.message.service;

import java.util.List;

import com.asiainfo.entity.FriendDynamicComment;

public interface FriendDynamicCommentService {
	
	String saveFriendDynamicComment(FriendDynamicComment friendDynamicComment);
	
	List<FriendDynamicComment> queryFriendDynamicCommentByDynamicId(String dynamicId);
}
