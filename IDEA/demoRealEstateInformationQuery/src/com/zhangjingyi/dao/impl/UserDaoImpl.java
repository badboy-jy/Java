package com.zhangjingyi.dao.impl;

import com.zhangjingyi.bean.User;
import com.zhangjingyi.dao.UserDao;
import com.zhangjingyi.utils.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends DbUtils implements UserDao {
    @Override
    public User login(User user) {
        User user1 = new User();
        List list = new ArrayList();
        StringBuffer sql =new StringBuffer("select * from user where card_id=? ");
        list.add(user.getCardId());
        if (user.getPassword()!=null){
            sql.append(" and password=? ");
            list.add(user.getPassword());
        }
        ResultSet query = query(sql.toString(), list);
        try {
            while (query.next()){
                user1.setCardId(query.getString("card_id"));
                user1.setPassword(query.getString("password"));
                user1.setName(query.getString("name"));
                user1.setSex(query.getInt("sex"));
                user1.setLastModifyTime(query.getTime("last_modify_time"));
                user1.setStatus(query.getInt("status"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeall();
        }
        return user1;

    }

    @Override
    public int regist(User user) {
        String sql = "insert into user values(?,?,?,?,?,1)";
        List list =new ArrayList();
        list.add(user.getCardId());
        list.add(user.getName());
        list.add(user.getSex());
        list.add(user.getLastModifyTime());
        list.add(user.getPassword());
        int update = update(sql, list);
        return update;
    }
}
