package com.badboyjy.service.impl;

import com.badboyjy.bean.User;
import com.badboyjy.dao.UserDao;
import com.badboyjy.dao.impl.UserDaoImpl;
import com.badboyjy.service.UserService;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();

	@Override
	public User login(User user) {
		User user2 = userDao.getUser(user);
		return user2;
	}

	@Override
	public int regist(User user) {
		return userDao.saveUser(user);
	}

	@Override
	public User checkUserByName(String username) {
		return userDao.checkUserByName(username);
	}

}
