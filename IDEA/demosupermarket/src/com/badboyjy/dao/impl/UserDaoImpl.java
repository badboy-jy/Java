package com.badboyjy.dao.impl;

import com.badboyjy.bean.User;
import com.badboyjy.dao.UserDao;
import com.badboyjy.utils.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends DbUtils implements UserDao {
    @Override
    public List<User> findalluser(String userName, int pageindex, int pagesize) {
        List<User> list = new ArrayList<User>();
        StringBuffer sql=
                new StringBuffer("select * from user ");
        List list1 = new ArrayList();
        //接参数数据  判断参数  通过判断拼接sql语句   填充参数到占位符
        if(!"".equals(userName)){
            sql.append(" where userName like ?  "); //前后一定要加空格
            list1.add("%"+userName+"%");
        }

        sql.append(" limit ?,?");

        list1.add((pageindex-1)*pagesize);
        list1.add(pagesize);
        ResultSet resultSet = query(sql.toString(), list1);
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPass(resultSet.getString("userPass"));
                user.setUserSex(resultSet.getString("userSex"));
                user.setUserAge(resultSet.getInt("userAge"));
                user.setUserPhone(resultSet.getString("userPhone"));
                user.setUserAddress(resultSet.getString("userAddress"));
                user.setUserQuanXian(resultSet.getInt("userQuanXian"));


                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return list;
    }

    @Override
    public int totalcount(String userName) {
        int count=0;
        try {
            StringBuffer sql=
                    new StringBuffer("select count(*) from user ");
            List list = new ArrayList();
            //接参数数据  判断参数  通过判断拼接sql语句   填充参数到占位符
            if(!"".equals(userName)){
                sql.append(" where userName like ?  "); //前后一定要加空格
                list.add("%"+userName+"%");
            }
            ResultSet resultSet = query(sql.toString(), list);
            while(resultSet.next()){
                //count=query.getInt("count(*)");
                count=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeall();
        }
        return count;
    }

    @Override
    public int adduser(User user) {
        int update = 0;
        try {
            String sql = "insert into user values(null,?,?,?,?,?,?,?)";
            ArrayList list = new ArrayList();
            list.add(user.getUserName());
            list.add(user.getUserPass());
            list.add(user.getUserSex());
            list.add(user.getUserAge());
            list.add(user.getUserPhone());
            list.add(user.getUserAddress());
            list.add(user.getUserQuanXian());
            update = update(sql, list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return update;
    }

    @Override
    public int delete(int parseInt) {
        int update = 0;
        String sql = "delete from user where user_id =?";
        List list = new ArrayList();
        list.add(parseInt);
        update = update(sql, list);
        return update;
    }

    @Override
    public User findbyid(int parseInt) {

        User user = new User();
        try {
            String sql = "select *  from user where user_id =?";
            List list = new ArrayList();
            list.add(parseInt);
            ResultSet resultSet = query(sql, list);
            while (resultSet.next()) {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPass(resultSet.getString("userPass"));
                user.setUserSex(resultSet.getString("userSex"));
                user.setUserAge(resultSet.getInt("userAge"));
                user.setUserPhone(resultSet.getString("userPhone"));
                user.setUserAddress(resultSet.getString("userAddress"));
                user.setUserQuanXian(resultSet.getInt("userQuanXian"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return user;
    }

    @Override
    public int update(User user) {
        List list= new ArrayList();
        int update = 0;
        try {
            String sql = "update user set userName=?,userPass=?,userSex=?,userAge=?,userPhone=?,userAddress=? where user_id=?";
            list.add(user.getUserName());
            list.add(user.getUserPass());
            list.add(user.getUserSex());
            list.add(user.getUserAge());
            list.add(user.getUserPhone());
            list.add(user.getUserAddress());
            list.add(user.getUserId());
            update = update(sql, list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return update;
    }
}
