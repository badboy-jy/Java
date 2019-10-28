package com.badboyjy.dao.impl;

import com.badboyjy.bean.District;
import com.badboyjy.dao.DistrictDao;
import com.badboyjy.utils.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistrictDaoImpl extends DbUtils implements DistrictDao {
    @Override
    public List<District> finddistrict() {
        List list=new ArrayList();
        try {
            String sql="select * from district";
            ResultSet resultSet = query(sql, null);
            while(resultSet.next()){
                //实例化category 对象 存放结果
                District district = new District();
                district.setId(resultSet.getInt("id"));
                district.setName(resultSet.getString("name"));
                //加对象到list里  返回list
                list.add(district);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeall();
        }
        return list;
    }

}
