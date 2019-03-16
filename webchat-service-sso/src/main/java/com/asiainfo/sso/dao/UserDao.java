package com.asiainfo.sso.dao;

import java.util.Map;

import com.asiainfo.entity.User;

public interface UserDao {

	User selectUserByToken(Map<String,String> map);
}
