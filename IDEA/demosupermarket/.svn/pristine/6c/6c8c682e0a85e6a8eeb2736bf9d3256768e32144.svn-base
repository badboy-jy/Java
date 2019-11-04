package com.badboyjy.service.impl;

import com.badboyjy.bean.User;
import com.badboyjy.dao.UserDao;
import com.badboyjy.dao.impl.UserDaoImpl;
import com.badboyjy.service.UserService;
import com.badboyjy.utils.DbUtils;

import java.util.List;

public class UserServiceImpl extends DbUtils implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findalluser(String userName, int pageindex, int pagesize) {
        return userDao.findalluser(userName,pageindex,pagesize);
    }

    @Override
    public int totalcount(String userName) {
        return userDao.totalcount(userName);
    }

    @Override
    public int adduser(User user) {
        return userDao.adduser(user);
    }

    @Override
    public int deletebyid(int parseInt) {
        return userDao.delete(parseInt);
    }

    @Override
    public User findbyid(int parseInt) {
        return userDao.findbyid(parseInt);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }
}
