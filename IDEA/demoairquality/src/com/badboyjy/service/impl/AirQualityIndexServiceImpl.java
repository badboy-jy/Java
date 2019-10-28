package com.badboyjy.service.impl;

import com.badboyjy.bean.AirQualityIndex;
import com.badboyjy.bean.District;
import com.badboyjy.dao.AirQualityIndexDao;
import com.badboyjy.dao.impl.AirQualityIndexDaoImpl;
import com.badboyjy.service.AirQualityIndexService;
import com.badboyjy.utils.DbUtils;

import java.util.List;

public class AirQualityIndexServiceImpl extends DbUtils implements AirQualityIndexService{
    AirQualityIndexDao airQualityIndexDao = new AirQualityIndexDaoImpl();
    @Override
    public List<AirQualityIndex> findall(int districtId, int pageindex, int pagesize) {
        return airQualityIndexDao.findall(districtId,pageindex,pagesize);
    }

    @Override
    public int totalcount(int districtId) {
        return airQualityIndexDao.totalcount(districtId);
    }

    @Override
    public int addAirQuality(AirQualityIndex airQualityIndex) {
        return airQualityIndexDao.addAirQuality(airQualityIndex);
    }

    @Override
    public AirQualityIndex findbyid(int parseInt) {
        return airQualityIndexDao.findbyid(parseInt);
    }

    @Override
    public int update(AirQualityIndex airQualityIndex) {
        return airQualityIndexDao.update(airQualityIndex);
    }

    @Override
    public int delete(int parseInt) {
        return airQualityIndexDao.delete(parseInt);
    }


}
