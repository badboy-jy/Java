package com.badboyjy.service;

import com.badboyjy.bean.User;

public interface UserService {
	User login(User user);

	int regist(User user);

	User checkUserByName(String username);
}
