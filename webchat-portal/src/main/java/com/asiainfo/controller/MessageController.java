package com.asiainfo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.entity.Friend;
import com.asiainfo.entity.SysLabel;
import com.asiainfo.entity.User;
import com.asiainfo.label.service.ConfigLabelService;
import com.asiainfo.label.service.LabelService;
import com.asiainfo.message.service.FriendService;
import com.asiainfo.sso.service.UserService;
import com.asiainfo.util.JsonUtil;
import com.asiainfo.util.WebResult;
import com.asiainfo.vo.FriendItemVo;

@RestController
public class MessageController {

	
	@Resource
	private ConfigLabelService configLabelService;
	@Resource
	private UserService userService;
	@Resource
	private FriendService friendService;
	@Resource
	private LabelService labelService;
	
	
	@RequestMapping("/loadFriends")
	public String loadFriends(Friend friend){
		
		List<Friend> friends = this.friendService.queryFriend(friend);
		if(friends.size()==0){
			return "";
		}
		List<FriendItemVo> itemVo = new ArrayList<FriendItemVo>();
		
		for (Friend f : friends) {
			String account=(f.getUserAccount().equals(friend.getUserAccount()))?f.getFriendAccount():f.getUserAccount();
			String remark=(f.getUserAccount().equals(friend.getUserAccount()))?f.getUserRemark():f.getFriendRemark();
			
			FriendItemVo item = new FriendItemVo();
			item.setAccount(account);
			item.setRemark(remark);
			itemVo.add(item);
		}
		List<User> users = this.userService.queryUserBySomeAccount(itemVo);
		List<SysLabel> sysLabels = this.labelService.querySysLabelPortrait(itemVo);
		
		for (FriendItemVo item : itemVo) {
			
			
			for (User u : users) {
				if(u.getAccount().equals(item.getAccount())){
					item.setNickname(u.getNickname());
					if(item.getRemark()==null || "".equals(item.getRemark())){
						
						item.setShowName(u.getNickname());
					}else{
						item.setShowName(item.getRemark());
					}
					break;
				}
			}
			
			
			for (SysLabel s : sysLabels) {
				if(s.getAccount().equals(item.getAccount())){
					
					item.setPortrait(s.getPortrait());
					break;
				}else{
					continue;
				}
			}
			
		}
		
		
		return JsonUtil.objectToJsonString(itemVo);
		
		
	}
	@RequestMapping("/agreeFriendApply")
	public String agreeFriendApply(Friend friend){
		
		
		try {
			this.friendService.modifyFriendAddStatusById(friend);
			return WebResult.AGREEN_FRIEND_APPLY_SUCCESS;
		} catch (Exception e) {
			return WebResult.AGREEN_FRIEND_APPLY_FAIL;
		}
		
	}
	
	
	
	
	@RequestMapping("/queryFriendApplyByme")
	public String queryFriendApplyByme(Friend friend){
		
		
		List<Friend> friends = this.friendService.queryFriendByUserAccount(friend);
		if(friends.size()==0){
			return "";
		}
		
		return JsonUtil.objectToJsonString(friends);
	}
	
	@RequestMapping("/queryFriendApplied")
	public String queryFriendApplied(Friend friend){
		
		
		List<Friend> friends = this.friendService.queryFriendByFriendAccount(friend);
		if(friends.size()==0){
			return "";
		}
		
		return JsonUtil.objectToJsonString(friends);
	}
	
	@RequestMapping("/addFriendWithValidateInfo")
	public String addFriendWithValidateInfo(Friend friend){
		friend.setAddStatus(0);
		
		try {
			this.friendService.saveFriendApply(friend);
			return WebResult.SEND_ADD_FRIEND_APPLY_SUCCESS;
		} catch (Exception e) {
			return WebResult.SEND_ADD_FRIEND_APPLY_FAIL;
		}
		
	}
	
	
	
	@RequestMapping("/addFriend")
	public String addFriend(String account,String friendAccount){
		
		if(account.equals(friendAccount)){
			return WebResult.FRIEND_NOT_SELF;
		}
		//要添加的用户是否不存在
		User user = this.userService.queryUserByAccount(friendAccount);
		if(user==null){
			return WebResult.USER_NOT_EXIST;
		}
		
		//要添加的用户是否已经是自己的好友，或正在申请中
		Friend f = new Friend();
		f.setUserAccount(account);
		f.setFriendAccount(friendAccount);
		Friend friend = this.friendService.getFriendByTwoAccount(f);
		if(friend!=null){
			if(friend.getAddStatus()==0){
				return WebResult.HAS_SEND_ADD_FRIEND_APPLY;
			}else{
				return WebResult.HAS_YOUR_FRIEND;
			}
		}
		
		String way = this.configLabelService.queryValidateWayByAccount(account);
		if("0".equals(way)){
			
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
