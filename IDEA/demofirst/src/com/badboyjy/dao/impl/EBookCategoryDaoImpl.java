package com.badboyjy.dao.impl;

import com.badboyjy.bean.EBookCategory;
import com.badboyjy.dao.EBookCategoryDao;
import com.badboyjy.utils.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EBookCategoryDaoImpl extends DbUtils implements EBookCategoryDao {
    @Override
    public List<EBookCategory> findtypes() {
        List list=new ArrayList();
        try {
            String sql="select * from ebook_category";
            ResultSet resultSet = query(sql, null);
            while(resultSet.next()){
                EBookCategory category = new EBookCategory();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
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
