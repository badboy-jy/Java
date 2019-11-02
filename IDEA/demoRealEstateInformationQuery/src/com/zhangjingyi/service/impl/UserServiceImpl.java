package com.zhangjingyi.service.impl;

import com.zhangjingyi.bean.User;
import com.zhangjingyi.dao.UserDao;
import com.zhangjingyi.dao.impl.UserDaoImpl;
import com.zhangjingyi.service.UserService;
import com.zhangjingyi.utils.DbUtils;

public class UserServiceImpl extends DbUtils implements UserService {
    UserDao userDao= new UserDaoImpl();
    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public int regist(User user) {
        return userDao.regist(user);
    }
}
