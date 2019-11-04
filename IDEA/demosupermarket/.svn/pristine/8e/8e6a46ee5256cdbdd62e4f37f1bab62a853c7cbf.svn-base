package com.badboyjy.service.impl;

import com.badboyjy.bean.User;
import com.badboyjy.dao.InDao;
import com.badboyjy.dao.impl.InDaoImpl;
import com.badboyjy.service.InService;
import com.badboyjy.utils.DbUtils;

public class InServiceImpl extends DbUtils implements InService {
    InDao inDao = new InDaoImpl();
    @Override
    public User login(User user) {
        return inDao.login(user);
    }
}
