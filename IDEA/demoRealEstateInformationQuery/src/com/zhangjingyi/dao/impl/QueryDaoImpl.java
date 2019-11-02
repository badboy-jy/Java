package com.zhangjingyi.dao.impl;

import com.zhangjingyi.bean.RealEstate;
import com.zhangjingyi.bean.User;
import com.zhangjingyi.dao.QueryDao;
import com.zhangjingyi.utils.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDaoImpl extends DbUtils implements QueryDao {
    @Override
    public int totalcount(String name, String cardId) {

        int count = 0;
        try {
            StringBuffer sql =
                    new StringBuffer("select count(*) from real_estate,user  where real_estate.card_id=user.card_id");
            List list = new ArrayList();
            //接参数数据  判断参数  通过判断拼接sql语句   填充参数到占位符
            if (!name.equals("") && !name.equals("全部")) {
                sql.append(" and user.name= ?  "); //前后一定要加空格
                list.add(name);
            }
            if (!cardId.equals("") && !cardId.equals("全部")) {
                sql.append(" and real_estate.card_id = ?  "); //前后一定要加空格
                list.add(cardId);
            }

            ResultSet resultSet = query(sql.toString(), list);
            while (resultSet.next()) {
                //count=query.getInt("count(*)");
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return count;
    }

    @Override
    public List<RealEstate> findall(String name, String cardId, int pageindex, int pagesize) {
        List<RealEstate> list = new ArrayList<RealEstate>();
        StringBuffer sql =
                new StringBuffer("select real_estate.*,user.card_id uid ,user.name  from user ,real_estate  " +
                        " where real_estate.card_id=user.card_id");
        List list1 = new ArrayList();
        //接参数数据  判断参数  通过判断拼接sql语句   填充参数到占位符
        if (!name.equals("") && !name.equals("全部")) {
            sql.append(" and user.name= ?  "); //前后一定要加空格
            list1.add(name);
        }
        if (!cardId.equals("") && !cardId.equals("全部")) {
            sql.append(" and real_estate.card_id = ?  "); //前后一定要加空格
            list1.add(cardId);
        }
        sql.append(" limit ?,?");

        list1.add((pageindex - 1) * pagesize);
        list1.add(pagesize);
        ResultSet resultSet = query(sql.toString(), list1);
        try {
            while (resultSet.next()) {
                RealEstate realEstate = new RealEstate();
                realEstate.setId(resultSet.getInt("id"));
                realEstate.setCardId(resultSet.getString("card_id"));
                realEstate.setProjectName(resultSet.getString("project_name"));
                realEstate.setAddress(resultSet.getString("address"));
                realEstate.setHouseType(resultSet.getString("house_type"));
                realEstate.setArea(resultSet.getInt("area"));
                realEstate.setBuildTime(resultSet.getDate("build_time"));


                User user = new User();
                user.setCardId(resultSet.getString("card_id"));
                user.setName(resultSet.getString("name"));

                realEstate.setUser(user);

                list.add(realEstate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return list;
    }

    @Override
    public int delete(int parseInt) {

        int update = 0;
        String sql = "delete from real_estate where id =?";
        List list = new ArrayList();
        list.add(parseInt);
        update = update(sql, list);
        return update;
    }

    @Override
    public RealEstate findbyid(int parseInt) {
        RealEstate realEstate = new RealEstate();
        try {
            String sql = "select *  from  real_estate where id =?";
            List list = new ArrayList();
            list.add(parseInt);
            ResultSet resultSet = query(sql, list);
            while (resultSet.next()) {
                realEstate.setId(resultSet.getInt("id"));
                realEstate.setCardId(resultSet.getString("card_id"));
                realEstate.setProjectName(resultSet.getString("project_name"));
                realEstate.setAddress(resultSet.getString("address"));
                realEstate.setHouseType(resultSet.getString("house_type"));
                realEstate.setArea(resultSet.getInt("area"));
                realEstate.setBuildTime(resultSet.getDate("build_time"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return realEstate;
    }

    @Override
    public int update(RealEstate realEstate) {

        List list = new ArrayList();
        int update = 0;
        try {
            String sql = "update real_estate set card_id=?,project_name=?,address=?,house_type=?,area=?,build_time=? where id=?";
            list.add(realEstate.getCardId());
            list.add(realEstate.getProjectName());
            list.add(realEstate.getAddress());
            list.add(realEstate.getHouseType());
            list.add(realEstate.getArea());
            list.add(realEstate.getBuildTime());
            list.add(realEstate.getId());
            update = update(sql, list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return update;
    }

    @Override
    public int add(RealEstate realEstate) {

        int update = 0;
        try {
            String sql = "insert into real_estate values(null,?,?,?,?,?,?)";
            ArrayList list = new ArrayList();
            list.add(realEstate.getCardId());
            list.add(realEstate.getProjectName());
            list.add(realEstate.getAddress());
            list.add(realEstate.getHouseType());
            list.add(realEstate.getArea());
            list.add(realEstate.getBuildTime());
            update = update(sql, list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return update;
    }


}
