package com.zhangjingyi.service;

import com.zhangjingyi.bean.User;

public interface UserService {
    public User login(User user);
    public int regist(User user);


}
