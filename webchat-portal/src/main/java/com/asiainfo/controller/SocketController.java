package com.asiainfo.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.asiainfo.entity.Friend;
import com.asiainfo.message.service.FriendService;
import com.asiainfo.sso.service.SessionManager;
import com.asiainfo.util.JsonUtil;
@Controller
public class SocketController {

	/**
	 * 通过SimpMessagingTemplate向浏览器发送消息
	 */
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@Resource
	private FriendService friendService;
	@Resource
	private SessionManager sessionManager;
	
	

	/**
	 * 当浏览器向服务端发送请求时，通过@MessageMapping映射/welcome这个地址，类似于@requestMapping
	 * 当服务端有消息时，会对订阅了@sendto中的路径的浏览器发送消息
	 */
	@MessageMapping("/sendUserStatus")
	@SendTo("/topic/getUserStatus")
	public String say(String message) throws Exception {
		return "";
	}
	

	@MessageMapping("/chat")
	public void handleChat(String body) throws Exception {
		Map<String, String> params = JsonUtil.jsonStringToMap(body);
		
		String sender=params.get("sender");
		String receiver=params.get("receiver");
		String msg=params.get("msg");
		String sendTime=params.get("sendTime");
		
		String showName="";
		Friend f = new Friend();
		f.setUserAccount(sender);
		f.setFriendAccount(receiver);
		Friend friend = friendService.getFriendByTwoAccount(f);
		if(friend.getUserAccount().equals(receiver)){
			if(friend.getUserRemark()==null || friend.getUserRemark().equals("")){
				showName = sessionManager.queryUser(sender).get("nickname").toString();
			}else{
				showName=friend.getUserRemark();
			}
		}else{
			if(friend.getFriendRemark()==null || friend.getFriendRemark().equals("")){
				showName = sessionManager.queryUser(sender).get("nickname").toString();
			}else{
				showName=friend.getFriendRemark();
			}
		}
		
		params.put("showName", showName);
		/*
		 * SimpMessagingTemplate有俩个推送的方法
		 * convertAndSend(destination, payload); //将消息广播到特定订阅路径中，类似@SendTo 
		 * convertAndSendToUser(user, destination, payload);//将消息推送到固定的用户订阅路径中，类似@SendToUser
		 */
		
		//参数分别为，接收消息的用户，浏览器订阅的地址，消息本身
		messagingTemplate.convertAndSendToUser(receiver,"/p2pchat", params);
		
	}
}
