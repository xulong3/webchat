package com.asiainfo.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	private static String ACTIVE_ACCOUNT_URL="http://localhost:8081/sso/activeAccount?account=";
	
	private static final String VIRTUAL_EMAIL="17853500586@163.com";
	
	
	public static void sendSimpleMail(String title,String receiver,String content) throws Exception{
		//开启虚拟邮箱
		if(VIRTUAL_EMAIL!=null){
			receiver=VIRTUAL_EMAIL;
		}
		
		Properties prop = new Properties();
		prop.setProperty("mail.host", "smtp.163.com");
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		//使用JavaMail发送邮件的5个步骤
		//1、创建session
		Session session = Session.getInstance(prop);
		//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(true);
		//2、通过session得到transport对象
		Transport ts = session.getTransport();
		
		//3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
		
		ts.connect("smtp.163.com", "13121398827", DESUtil.decryption("XXWwxaUX5VR77SBtOCZSsg==", null));
		
		
		//4、创建邮件对象
		MimeMessage message = new MimeMessage(session);
		//指明邮件的发件人
		
		message.setFrom(new InternetAddress(getSender()));
		
		//指明邮件的收件人
		
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
		//邮件的标题
		message.setSubject(title);
		//邮件的文本内容
		message.setContent(content, "text/html;charset=UTF-8");
		
		
		//5、发送邮件
		ts.sendMessage(message,message.getAllRecipients());
		
		ts.close();
		
		
	}
	
	
	
	
	private static String getSender(){
		return "13121398827@163.com";
	}
	
	
	public static String getActiveAccountTemplate(String account){
		ACTIVE_ACCOUNT_URL=ACTIVE_ACCOUNT_URL+account;
		return "您好!欢迎您加入网页聊天室,您的聊天账号为"+account+",请在三天内点击<a href='"+ACTIVE_ACCOUNT_URL+"'>此处</a>激活该账号,否则账号将会不可用!";
	}
	
	
	
}
