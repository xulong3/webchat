package com.asiainfo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.entity.Friend;
import com.asiainfo.entity.User;
import com.asiainfo.label.service.ConfigLabelService;
import com.asiainfo.message.service.FriendService;
import com.asiainfo.sso.service.UserService;
import com.asiainfo.util.WebResult;

@RestController
public class MessageController {

	
	@Resource
	private ConfigLabelService configLabelService;
	@Resource
	private UserService userService;
	@Resource
	private FriendService friendService;
	
	
	
	
	@RequestMapping("/addFriend")
	public String addFriend(String account,String friendAccount){
		if(account.equals(friendAccount)){
			return WebResult.FRIEND_NOT_SELF;
		}
		
		User user = this.userService.queryUserByAccount(friendAccount);
		if(user==null){
			return WebResult.USER_NOT_EXIST;
		}
		String way = this.configLabelService.queryValidateWayByAccount(account);
		if("0".equals(way)){
			Friend f = new Friend();
			f.setUserAccount(account);
			f.setFriendAccount(friendAccount);
			f.setAddStatus(1);
			try {
				this.friendService.saveFriendApply(f);
				return WebResult.ADD_FRIEND_SUCCESS;
			} catch (Exception e) {
				return WebResult.ADD_FRIEND_FAIL;
			}
		}
		if("1".equals(way)){
			return WebResult.NEED_VALIDATE_INFO;
		}
		if("2".equals(way)){
			return WebResult.ADD_FRIEND_REFUSED;
		}
		
		
		return null;
	}
	
	
}
