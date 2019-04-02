package com.asiainfo;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageServiceStarter {

	private static ClassPathXmlApplicationContext ctx;

	public static void main(String[] args) throws IOException {
		ctx = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
		ctx.start();
		System.in.read();
		
		
	}
}
