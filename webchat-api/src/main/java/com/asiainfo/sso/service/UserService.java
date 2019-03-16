package com.asiainfo.sso.service;

import java.util.Map;

import com.asiainfo.entity.User;

public interface UserService {
	/**
	 * 增加新用户
	 * @param user 被增加的用户
	 * @return 增加成功，返回新账号，失败，则抛出异常
	 */
	String saveUser(User user);
	/**
	 * 获取一个新账号
	 * @return 新的账号
	 */
	String getNewAccount();
	/**
	 * 发送激活账号的邮件
	 * @return 发送成功返回yes，发送失败返回no
	 */
	String sendActiveAccountEmail(User user);
	/**
	 * 激活账号
	 * @param account 要激活的账号
	 * @return 激活成功返回yes，失败返回no
	 */
	String activeAccount(String account);
	/**
	 * 通过账号登录
	 * @param user 要被登录的用户
	 * @return 登录成功返回yes，失败则抛出异常
	 */
	String loginByAccount(User user);
	
	/**
	 * 通过邮箱登录
	 * @param user 要被登录的用户
	 * @return 登录成功返回yes，失败则抛出异常
	 */
	String loginByEmail(User user);
	
	/**
	 * 通过邮箱查询user的数量
	 * @param email 用户邮箱
	 * @return 用户数量
	 */
	int queryUserCountByEmail(String email);
	
	/**
	 * 用户是否可用
	 * @param account 被查询的用户账号
	 * @return yes表示可用，no表示不可用
	 */
	String isUserAvailable(String account);
	/**
	 * 根据账号或email查询用户
	 * @param map
	 * @return 用户对象
	 */
	User queryUserByToken(Map<String,String> map);
}
