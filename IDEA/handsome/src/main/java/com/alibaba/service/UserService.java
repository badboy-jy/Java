package com.alibaba.service;

import com.alibaba.bean.User;

import java.util.List;

public interface UserService {

    public List<User> userList() throws Exception;

    public User login(User user) throws Exception;

}
