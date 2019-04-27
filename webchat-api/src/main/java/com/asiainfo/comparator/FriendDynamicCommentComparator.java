package com.asiainfo.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.asiainfo.entity.FriendDynamicComment;

public class FriendDynamicCommentComparator implements Serializable,Comparator<FriendDynamicComment>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	@Override
	public int compare(FriendDynamicComment dc1, FriendDynamicComment dc2) {
		if(dc1.getCommentTime().getTime()>dc2.getCommentTime().getTime()){
			
			return -1;
		}
		return 1;
	}
}
