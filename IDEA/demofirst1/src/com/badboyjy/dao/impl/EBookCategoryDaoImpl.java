package com.badboyjy.dao.impl;

import com.badboyjy.bean.EBookCategory;
import com.badboyjy.dao.EBookCategoryDao;
import com.badboyjy.utils.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EBookCategoryDaoImpl extends DbUtils implements EBookCategoryDao {

    //实现接口  重写方法
    // 查询所有name列  select
    @Override
    public List<EBookCategory> findtypes() {
        List list=new ArrayList();
        try {
            String sql="select * from ebook_category";
            ResultSet resultSet = query(sql, null);
            while(resultSet.next()){
                //实例化category 对象 存放结果
                EBookCategory category = new EBookCategory();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                //加对象到list里  返回list
                list.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeall();
        }
        return list;
    }

}
