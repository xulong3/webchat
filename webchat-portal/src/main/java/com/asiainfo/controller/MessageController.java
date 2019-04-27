package com.asiainfo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.comparator.FriendDynamicComparator;
import com.asiainfo.entity.ChatList;
import com.asiainfo.entity.Friend;
import com.asiainfo.entity.FriendDynamic;
import com.asiainfo.entity.FriendDynamicPraise;
import com.asiainfo.entity.SysLabel;
import com.asiainfo.entity.User;
import com.asiainfo.label.service.ConfigLabelService;
import com.asiainfo.label.service.LabelService;
import com.asiainfo.message.service.ChatListService;
import com.asiainfo.message.service.FriendDynamicPraiseService;
import com.asiainfo.message.service.FriendDynamicService;
import com.asiainfo.message.service.FriendService;
import com.asiainfo.sso.service.UserService;
import com.asiainfo.util.JsonUtil;
import com.asiainfo.util.WebResult;
import com.asiainfo.vo.FriendDynamicVo;
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
	@Resource
	private ChatListService chatListService;
	@Resource
	private FriendDynamicService friendDynamicService;
	@Resource
	private FriendDynamicPraiseService friendDynamicPraiseService;
	
	
	@RequestMapping("/loadFriends")
	public String loadFriends(Friend friend){
		
		
		
		List<Friend> friends = this.friendService.queryFriend(friend);
		if(friends.size()==0){
			return "";
		}
		List<FriendItemVo> itemVo = new ArrayList<FriendItemVo>();
		//遍历每一个friend
		for (Friend f : friends) {
			//获取好友的账号
			String account=(f.getUserAccount().equals(friend.getUserAccount()))?f.getFriendAccount():f.getUserAccount();
			//获取好友的备注
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
				}
			}
			for (SysLabel s : sysLabels) {
				
				if(s.getAccount().equals(item.getAccount())){
					item.setPortrait(s.getPortrait());
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
		
		String way = this.configLabelService.queryValidateWayByAccount(friendAccount);
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
	
	@RequestMapping("/removeFriendApply")
	public String removeFriendApply(Friend friend,String type){
		friend.setAddStatus(0);
		String res = this.friendService.removeFriend(friend);
		
		if(type.equals("0")){
			
			return WebResult.FRIEND_APPLY_CANCEL_SUCCESS;
		}
		if(type.equals("1")){
			return WebResult.FRIEND_APPLY_REFUSE_SUCCESS;

		}
		return null;
	}
	
	@RequestMapping("/loadFriendAllLabel")
	public String loadFriendAllLabel(String account){
		String sysLabel = this.labelService.querySysLabelCache(account);
		String label = this.labelService.queryLabelCache(account);
		User user = this.userService.queryUserByAccount(account);
		String userJson = JsonUtil.objectToJsonString(user);
		
		if(!label.equals("")){
			
			return "["+userJson+","+sysLabel+","+label+"]";
		}
		
		return "["+userJson+","+sysLabel+"]";
		
	}
	
	@RequestMapping("/saveOrUpdateChatList")
	public String saveOrUpdateChatList(ChatList chatList){
		String res = this.chatListService.saveOrUpdateChatList(chatList);
		return WebResult.BLANK;
	}
	
	@RequestMapping("/getCurrentChatList")
	public String getCurrentChatList(String account){
		ChatList cl = this.chatListService.selectChatListByAccount(account);
		if(cl==null){
			return WebResult.BLANK;
		}
		FriendItemVo fvo = new FriendItemVo();
		User user = this.userService.queryUserByAccount(cl.getFriendAccount());
		fvo.setAccount(user.getAccount());
		fvo.setNickname(user.getNickname());
		
		List<FriendItemVo> list = new ArrayList<FriendItemVo>();
		FriendItemVo vo = new FriendItemVo();
		vo.setAccount(user.getAccount());
		list.add(vo);
		fvo.setPortrait(this.labelService.querySysLabelPortrait(list).get(0).getPortrait());
		
		Friend f = new Friend();
		f.setUserAccount(account);
		f.setFriendAccount(user.getAccount());
		Friend friend = this.friendService.getFriendByTwoAccount(f);
		fvo.setRemark(friend.getUserAccount().equals(user.getAccount())?friend.getFriendRemark():friend.getUserRemark());
		
		
		return JsonUtil.objectToJsonString(fvo);
	}
	
	
	@RequestMapping("/saveFriendDynamic")
	public String saveFriendDynamic(FriendDynamic friendDynamic){
		friendDynamic.setDynamicId(UUID.randomUUID().toString().replace("-", ""));
		friendDynamic.setPraiseCount(0);
		friendDynamic.setPublishTime(new Date());
		
		String res = this.friendDynamicService.saveFriendDynamic(friendDynamic);
		
		return WebResult.DYNAMIC_PUBLISH_SUCCESS;
	}
	
	@RequestMapping("/queryFriendDynamicSelf")
	public String queryFriendDynamicSelf(String account){
		List<FriendDynamicVo> list = new ArrayList<FriendDynamicVo>();
		
		List<FriendDynamic> dynamics = this.friendDynamicService.queryFriendDynamicSelf(account);
		
		if(dynamics==null || dynamics.size()==0){
			return WebResult.BLANK;
		}
		
		
		//按照时间排序
		Collections.sort(dynamics,new FriendDynamicComparator());
		
		//根据账号查头像
		String portrait = this.labelService.querySysLabelPortraitOne(account);
		//查昵称
		User user = this.userService.queryUserByAccount(account);
		
		
		
		for (FriendDynamic dynamic : dynamics) {
			FriendDynamicVo vo = new FriendDynamicVo();
			vo.setAccount(dynamic.getAccount());
			vo.setDynamicId(dynamic.getDynamicId());
			vo.setPraiseCount(dynamic.getPraiseCount()+"");
			vo.setPublishTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(dynamic.getPublishTime()));
			vo.setTextContent(dynamic.getTextContent());
			vo.setPortrait(portrait);
			vo.setNickname(user.getNickname());
			//查某一个人的动态时，不设备注
			
			list.add(vo);
		}
		
		
		return JsonUtil.listObjectToJsonString(list);
		
	}
	
	
	@RequestMapping("/queryFriendDynamic")
	public String queryFriendDynamic(String account){
		List<FriendDynamicVo> vos = new ArrayList<FriendDynamicVo>();
		
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		Friend f = new Friend();
		f.setAddStatus(1);
		f.setUserAccount(account);
		List<Friend> friends = this.friendService.queryFriend(f);
		
		if(friends!=null && friends.size()>0){
			for (Friend friend : friends) {
				
				Map<String,String> params = new HashMap<String,String>();
				params.put("account", friend.getUserAccount().equals(account)?friend.getFriendAccount():friend.getUserAccount());
				//根据账号查朋友圈权限
				String friendCircle = this.configLabelService.queryFriendCircleByAccount(params.get("account"));
				//
				if(friendCircle.equals("2")){
					//表示任何人不可见
					continue;
				}
				params.put("friendCircle", friendCircle);
				list.add(params);
			}
		}
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("account", account);
		params.put("friendCircle", "0");
		list.add(params);
		
		List<FriendDynamic> allDynamic = this.friendDynamicService.queryFriendDynamicAll(list);
		Collections.sort(allDynamic,new FriendDynamicComparator());
		
		
		for (FriendDynamic dynamic : allDynamic) {
			FriendDynamicVo vo = new FriendDynamicVo();
			vo.setAccount(dynamic.getAccount());
			vo.setDynamicId(dynamic.getDynamicId());
			vo.setPraiseCount(dynamic.getPraiseCount()+"");
			vo.setPublishTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(dynamic.getPublishTime()));
			vo.setTextContent(dynamic.getTextContent());
			
			//根据账号查头像
			String portrait = this.labelService.querySysLabelPortraitOne(dynamic.getAccount());
			//查昵称
			User user = this.userService.queryUserByAccount(dynamic.getAccount());
			vo.setPortrait(portrait);
			vo.setNickname(user.getNickname());
			
			
			//查备注
			if(account.equals(dynamic.getAccount())){
				vo.setRemark("");
			}else{
				Friend friend = new Friend();
				friend.setUserAccount(account);
				friend.setFriendAccount(dynamic.getAccount());
				friend = this.friendService.getFriendByTwoAccount(friend);
				vo.setRemark(friend.getUserAccount().equals(dynamic.getAccount())?friend.getFriendRemark():friend.getUserRemark());
			}
			
			
			vos.add(vo);
		}
		
		return JsonUtil.listObjectToJsonString(vos);
		
	}
	
	
	@RequestMapping("/saveFriendDynamicPraise")
	public String saveFriendDynamicPraise(FriendDynamicPraise friendDynamicPraise){
		
		String res = this.friendDynamicPraiseService.saveFriendDynamicPraise(friendDynamicPraise);
		
		return WebResult.SUCCESS;
	}
	
	@RequestMapping("/isPraise")
	public String isPraise(FriendDynamicPraise friendDynamicPraise){
		
		int count = this.friendDynamicPraiseService.queryFriendDynamicPraiseCount(friendDynamicPraise);
		
		if(count==0){
			return WebResult.FALSE;
			
		}else{
			return WebResult.TRUE;

		}
	}
	
	
	@RequestMapping("/addPraise")
	public String addPraise(FriendDynamicPraise friendDynamicPraise){
		friendDynamicPraise.setPraiseId(UUID.randomUUID().toString().replace("-", ""));
		friendDynamicPraise.setPraiseTime(new Date());
		
		String res = this.friendDynamicPraiseService.saveFriendDynamicPraise(friendDynamicPraise);
		String res2 = this.friendDynamicService.modifyFriendDynamicPraiseCount(friendDynamicPraise.getDynamicId());
		
		return WebResult.SUCCESS;
	}
	
	@RequestMapping("/removePraise")
	public String removePraise(FriendDynamicPraise friendDynamicPraise){
		
		
		String res = this.friendDynamicPraiseService.deleteFriendDynamicPraise(friendDynamicPraise);
		String res2 = this.friendDynamicService.modifyFriendDynamicPraiseCountMinus(friendDynamicPraise.getDynamicId());
		
		return WebResult.SUCCESS;
	}
	
	
}
