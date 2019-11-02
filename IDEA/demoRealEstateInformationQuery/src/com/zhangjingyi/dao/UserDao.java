package com.zhangjingyi.dao;

import com.zhangjingyi.bean.User;

public interface UserDao {
    public User login(User user);

    public int regist(User user);
}
