package com.badboyjy.dao.impl;

import com.badboyjy.bean.AirQualityIndex;
import com.badboyjy.bean.District;
import com.badboyjy.dao.AirQualityIndexDao;
import com.badboyjy.utils.DbUtils;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirQualityIndexDaoImpl extends DbUtils implements AirQualityIndexDao {
    @Override
    public List<AirQualityIndex> findall(int districtId, int pageindex, int pagesize) {
        List<AirQualityIndex> list = new ArrayList<AirQualityIndex>();
        StringBuffer sql=
                new StringBuffer("select air_quality_index.*,district.id did,district.name  from air_quality_index ,district  " +
                        " where district.id=air_quality_index.district_id");
        List list1 = new ArrayList();
        //接参数数据  判断参数  通过判断拼接sql语句   填充参数到占位符
        if(districtId!=0){
            sql.append(" and district.id=?  "); //前后一定要加空格
            list1.add(districtId);
        }

        sql.append(" limit ?,?");

        list1.add((pageindex-1)*pagesize);
        list1.add(pagesize);
        ResultSet resultSet = query(sql.toString(), list1);
        try {
            while (resultSet.next()) {
                AirQualityIndex airQualityIndex = new AirQualityIndex();
                airQualityIndex.setId(resultSet.getInt("id"));
                airQualityIndex.setDistrictId(resultSet.getInt("district_id"));
                airQualityIndex.setMonitorTime(resultSet.getDate("monitor_time"));
                airQualityIndex.setPm10(resultSet.getInt("pm10"));
                airQualityIndex.setPm2_5(resultSet.getInt("pm2_5"));
                airQualityIndex.setMonitoringStation(resultSet.getString("monitoring_station"));
                airQualityIndex.setLastMondifyTime(resultSet.getDate("last_modify_time"));

                District district = new District();
                district.setId(resultSet.getInt("did"));
                district.setName(resultSet.getString("name"));

                airQualityIndex.setDistrict(district);

                list.add(airQualityIndex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return list;
    }

    @Override
    public int totalcount(int districtId) {
        int count=0;
        try {
//            String sql="select count(*) from ebook_entry where ebook_entry.title like ? ";
            //动态sql
            StringBuffer sql=
                    new StringBuffer("select count(*) from district,air_quality_index  where district.id=air_quality_index.district_id");
            List list = new ArrayList();
            //接参数数据  判断参数  通过判断拼接sql语句   填充参数到占位符
            if(districtId!=0){
                sql.append(" and district.id=?  "); //前后一定要加空格
                list.add(districtId);
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
    public int addAirQuality(AirQualityIndex airQualityIndex) {
        int update = 0;
        try {
            String sql = "insert into air_quality_index values(null,?,?,?,?,?,?)";
            ArrayList list = new ArrayList();
            list.add(airQualityIndex.getDistrictId());
            list.add(airQualityIndex.getMonitorTime());
            list.add(airQualityIndex.getPm10());
            list.add(airQualityIndex.getPm2_5());
            list.add(airQualityIndex.getMonitoringStation());
            list.add(airQualityIndex.getLastMondifyTime());
            update = update(sql, list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return update;
    }

    @Override
    public AirQualityIndex findbyid(int did) {

        AirQualityIndex airQualityIndex = new AirQualityIndex();
        try {
            String sql = "select * from air_quality_index where air_quality_index.id =?";
            List list = new ArrayList();
            list.add(did);
            ResultSet resultSet = query(sql, list);
            while (resultSet.next()) {
                airQualityIndex.setId(resultSet.getInt("id"));
                airQualityIndex.setDistrictId(resultSet.getInt("district_id"));
                airQualityIndex.setMonitorTime(resultSet.getDate("monitor_time"));
                airQualityIndex.setPm10(resultSet.getInt("pm10"));
                airQualityIndex.setPm2_5(resultSet.getInt("pm2_5"));
                airQualityIndex.setMonitoringStation(resultSet.getString("monitoring_station"));
                airQualityIndex.setLastMondifyTime(resultSet.getDate("last_modify_time"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return airQualityIndex;
    }

    @Override
    public int update(AirQualityIndex airQualityIndex) {

        int update = 0;
        try {
            String sql="update air_quality_index set district_id=?,monitor_time=?,pm10=?,pm2_5=?,monitoring_station=?,last_modify_time=?where id=?";
            List params=new ArrayList();
            params.add(airQualityIndex.getDistrictId());
            params.add(airQualityIndex.getMonitorTime());
            params.add(airQualityIndex.getPm10());
            params.add(airQualityIndex.getPm2_5());
            params.add(airQualityIndex.getMonitoringStation());
            params.add(airQualityIndex.getLastMondifyTime());
            params.add(airQualityIndex.getId());

            update = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeall();
        }
        return update;
    }

    @Override
    public int delete(int parseInt) {
        int i =0;
        try {
            String sql="delete from air_quality_index where id=?";
            List list=new ArrayList();
            list.add(parseInt);
            i= update(sql, list);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeall();
        }
        return i;
    }

}
