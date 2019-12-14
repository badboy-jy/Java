package com.alibaba.service.impl;

import com.alibaba.bean.User;
import com.alibaba.bean.UserExample;
import com.alibaba.dao.UserMapper;
import com.alibaba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(User user) throws Exception {
		User user1=userMapper.selectByUser(user);
		return user1;
	}

	@Override
	public List<User> userList() throws Exception {
		// TODO Auto-generated method stub
		UserExample example=new UserExample();
		List<User> list=userMapper.selectByExample(example);
		System.out.println("123"+list);
		return list;
	}



	
}
