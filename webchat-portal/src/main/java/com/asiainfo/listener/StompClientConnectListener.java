package com.asiainfo.listener;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import com.asiainfo.sso.service.SessionManager;
import com.asiainfo.util.LoggerUtil;

@Component
public class StompClientConnectListener implements ApplicationListener<SessionConnectEvent>  {

	@Resource
	private SessionManager sessionManager;
	
	@Override
	public void onApplicationEvent(SessionConnectEvent e) {
		
		Message<byte[]> m = e.getMessage();
		
		MessageHeaders mh = m.getHeaders();
		Map<String,Object> headers = (Map<String,Object>)mh.get("nativeHeaders");
		String account = headers.get("sid").toString();
		account=account.substring(1, account.length()-1);
		
		sessionManager.bindStompSidWithAccount(mh.get("simpSessionId").toString(), account);
		
		
		sessionManager.modifyUserStatus(account, 1);
		
		LoggerUtil.info(this.getClass(), "---------------------客户端连接了-------------------");
		
		
	}

	
	
	
}
