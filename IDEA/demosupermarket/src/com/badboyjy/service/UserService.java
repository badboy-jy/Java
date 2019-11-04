package com.badboyjy.service;

import com.badboyjy.bean.User;

import java.util.List;

public interface UserService {
    public List<User> findalluser(String userName, int pageindex, int pagesize);

    public int totalcount(String userName);

    public int adduser(User user);

    public int deletebyid(int parseInt);

    public User findbyid(int parseInt);

    public int update(User user);
}
