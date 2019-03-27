package com.asiainfo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
/**
 * 通过@EnableWebSocketMessageBroker注解开启使用stomp协议来传输基于代理（message broker）的消息，
 * 这时控制器支持使用@messageMapping,就像使用@requestMapping一样
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	/**
	 * 注册stomp协议的节点
	 */
	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
		//注册一个stomp的endpoint并映射指定的url，并指定使用sockjs协议
        registry.addEndpoint("/endpointWisely").setAllowedOrigins("*").withSockJS(); 
        //注册一个名为endpointChat的endpoint
        registry.addEndpoint("/endpointChat").setAllowedOrigins("*").withSockJS();
    }


	/**
	 * 配置消息代理
	 */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	//广播式应配置一个/topic的消息代理，点对点式应增加一个queue代理
        registry.enableSimpleBroker("/user","/topic"); 
        //全局使用的消息前缀（客户端订阅路径上会体现出来）
        //registry.setApplicationDestinationPrefixes("/app");
        //点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        //registry.setUserDestinationPrefix("/user/");
        
        //例如客户端发送消息的目的地为/app/send，则对应控制层@MessageMapping(“/send”) 
        //客户端订阅主题的目的地为/app/subscribe，则对应控制层@SubscribeMapping(“/subscribe”)
    }
    
    
    

}
