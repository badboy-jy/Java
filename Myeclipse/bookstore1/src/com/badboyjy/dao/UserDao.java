package com.badboyjy.dao;

import com.badboyjy.bean.User;

public interface UserDao {
	User getUser(User user);

	int saveUser(User user);

	User checkUserByName(String name);
}
