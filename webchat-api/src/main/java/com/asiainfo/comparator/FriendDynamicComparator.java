package com.asiainfo.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.asiainfo.entity.FriendDynamic;

public class FriendDynamicComparator implements Serializable,Comparator<FriendDynamic>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(FriendDynamic fd1, FriendDynamic fd2) {
		if(fd1.getPublishTime().getTime()>fd2.getPublishTime().getTime()){
			
			return -1;
		}
		return 1;
	}
}
