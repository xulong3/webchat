package com.asiainfo.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.asiainfo.util.service.JdbcService;
import com.asiainfo.util.service.impl.JdbcServiceImpl;

public class EmailUtil {
	private static JdbcService jdbcService=new JdbcServiceImpl();
	
	
	public static void sendSimpleMail(String title,String receiver,String content) throws Exception{
		
		
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
		String sender = getSender();
		ts.connect("smtp.163.com", sender.replace("@163.com", ""), DESUtil.decryption(getSenderPassword(), getDesKey()));
		
		
		//4、创建邮件对象
		MimeMessage message = new MimeMessage(session);
		//指明邮件的发件人
		
		message.setFrom(new InternetAddress(sender));
		
		//指明邮件的收件人
		//查询虚拟邮箱
		String virtualEmail = getVirtualEmail();
		if(virtualEmail!=null){
			receiver=virtualEmail; 
		}
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
		//邮件的标题
		message.setSubject(title);
		//邮件的文本内容
		message.setContent(content, "text/html;charset=UTF-8");
		
		
		//5、发送邮件
		ts.sendMessage(message,message.getAllRecipients());
		
		ts.close();
		
		
	}
	
	private static String getVirtualEmail() throws Exception{
		String status = jdbcService.queryConfigValueByKey("virtual_email_status");
		if("on".equals(status)){
			String virtualEmail = jdbcService.queryConfigValueByKey("virtual_email");
			return virtualEmail;
		}else{
			 
			return null;
		}
	}
	
	
	
	public static String getSender() throws Exception{
		String sender = jdbcService.queryConfigValueByKey("email_sender");
		return sender;
	}
	
	
	public static String getSenderPassword() throws Exception{
		String password = jdbcService.queryConfigValueByKey("email_sender_password");
		return password;
	}
	
	public static String getDesKey() throws Exception{
		String key = jdbcService.queryConfigValueByKey("des_key");
		return key;
	}
	
	public static String getActiveAccountTemplate(String account){
		String url="http://localhost:8081/activeAccount?account="+account;
		return "您好!欢迎您加入网页聊天室,您的聊天账号为"+account+",请在三天内点击<a href='"+url+"'>此处</a>激活该账号,否则账号将会不可用!";
	}
	
	
	
}
