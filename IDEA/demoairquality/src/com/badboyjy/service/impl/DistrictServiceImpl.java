package com.badboyjy.service.impl;

import com.badboyjy.bean.District;
import com.badboyjy.dao.DistrictDao;
import com.badboyjy.dao.impl.DistrictDaoImpl;
import com.badboyjy.service.DistrictService;
import com.badboyjy.utils.DbUtils;

import java.util.List;

public class DistrictServiceImpl extends DbUtils implements DistrictService {
    DistrictDao districtDao = new DistrictDaoImpl();
    @Override
    public List<District> finddistrict() {
        return districtDao.finddistrict();
    }
}
