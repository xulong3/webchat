package com.asiainfo.sso.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.asiainfo.entity.User;
import com.asiainfo.sso.dao.UserDao;
import com.asiainfo.sso.service.UserService;

public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	@Override
	public String saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNewAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendActiveAccountEmail(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String activeAccount(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loginByAccount(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loginByEmail(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int queryUserCountByEmail(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String isUserAvailable(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User queryUserByToken(Map<String, String> map) {
		User user = this.userDao.selectUserByToken(map);
		return user;
	}

}
