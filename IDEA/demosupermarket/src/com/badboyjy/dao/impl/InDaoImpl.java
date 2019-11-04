package com.badboyjy.dao.impl;

import com.badboyjy.bean.User;
import com.badboyjy.dao.InDao;
import com.badboyjy.utils.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InDaoImpl extends DbUtils implements InDao {
    @Override
    public User login(User user) {
        List list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from user where userName=?");
        list.add(user.getUserName());
        if (user.getUserPass()!=null)
        {
            sql.append(" and userPass =? ");
            list.add(user.getUserPass());
        }
        ResultSet query = query(sql.toString(), list);
        User user1 = new User();
        try {
            while (query.next()){
                user1.setUserName(query.getString("userName"));
                user1.setUserPass(query.getString("userPass"));
                user1.setUserQuanXian(query.getInt("userQuanXian"));
                user1.setUserId(query.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user1;
    }
}
