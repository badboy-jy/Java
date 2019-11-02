package com.badboyjy.dao.impl;

import com.badboyjy.bean.User;
import com.badboyjy.dao.BaseDao;
import com.badboyjy.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		String sql = "select * from user where user_name =? and password =?";
		User user2 = this.getBean(sql, user.getUserName(), user.getPassword());
		return user2;
	}

	@Override
	public int saveUser(User user) {
		String sql = "insert into user values(null,?,?,?)";
		return update(sql, user.getUserName(), user.getPassword(), user.getEmail());

	}

	@Override
	public User checkUserByName(String name) {
		String sql = "select * from user where user_name= ?";

		return this.getBean(sql, name);
	}

}
