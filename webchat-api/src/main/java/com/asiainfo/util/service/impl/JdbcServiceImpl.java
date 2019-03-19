package com.asiainfo.util.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Service;

import com.asiainfo.util.service.JdbcService;

@Service("jdbcService")
public class JdbcServiceImpl implements JdbcService{

	private Connection conn;
	void init() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/webchat","root","ok");
	}
	
	@Override
	public String queryConfigValueByKey(String key) throws Exception {
		init();
		String sql="select c.`value` from config c where c.`key`=?";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, key);
		ResultSet rs = stmt.executeQuery();
		String value=null;
		while(rs.next()){
			value = rs.getString("value");
		}
		conn.close();
		
		return value;
	}
	

}
