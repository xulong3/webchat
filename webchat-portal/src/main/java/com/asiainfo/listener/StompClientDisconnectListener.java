package com.asiainfo.listener;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.asiainfo.sso.service.SessionManager;
import com.asiainfo.util.LoggerUtil;

@Component
public class StompClientDisconnectListener implements ApplicationListener<SessionDisconnectEvent>  {

	@Resource
	private SessionManager SessionManager;
	
	@Override
	public void onApplicationEvent(SessionDisconnectEvent event) {
		
		Message<byte[]> m = event.getMessage();
		
		MessageHeaders mh = m.getHeaders();
		String sid = mh.get("simpSessionId").toString();
		String account = SessionManager.getAccountBySid(sid);
		
		SessionManager.modifyUserStatus(account, 0);
		
		LoggerUtil.info(this.getClass(),"--------------------客户端"+account+"断开连接了----------------------------");
		
	}

	
	

	
}
