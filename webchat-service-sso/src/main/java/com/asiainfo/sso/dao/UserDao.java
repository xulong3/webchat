package com.asiainfo.sso.dao;

import java.util.List;
import java.util.Map;

import com.asiainfo.entity.User;
import com.asiainfo.vo.FriendItemVo;

public interface UserDao {
	/**
	 * 通过token（账号或密码）查询用户
	 * @param map
	 * @return
	 */
	User selectUserByToken(Map<String,String> map);
	/**
	 * 通过email查询用户的数量
	 * @param email
	 * @return
	 */
	int selectUserCountByEmail(String email);
	
	/**
	 * 查询user表中最大的id
	 * @return
	 */
	int selectUserMaxId();
	
	/**
	 * 插入用户
	 * @param user
	 * @return
	 */
	int insertUser(User user);
	
	/**
	 * 查询用户是否可用
	 * @param account
	 * @return
	 */
	int selectUserAvailable(String account);
	
	int updateUserAvailable(User user);
	
	int updateUserActTime(User user);
	
	User selectUserByAccount(String account);
	
	List<User> selectUserBySomeAccount(List<FriendItemVo> list);
	
	
}
