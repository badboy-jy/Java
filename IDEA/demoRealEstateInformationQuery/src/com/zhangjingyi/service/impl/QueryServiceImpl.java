package com.zhangjingyi.service.impl;

import com.zhangjingyi.bean.RealEstate;
import com.zhangjingyi.dao.QueryDao;
import com.zhangjingyi.dao.impl.QueryDaoImpl;
import com.zhangjingyi.service.QueryService;
import com.zhangjingyi.utils.DbUtils;

import java.util.List;

public class QueryServiceImpl extends DbUtils implements QueryService {
    QueryDao queryDao=new QueryDaoImpl();
    @Override
    public int totalcount(String name, String cardId) {
        return queryDao.totalcount(name,cardId);
    }

    @Override
    public List<RealEstate> findall(String name, String cardId, int pageindex, int pagesize) {
        return queryDao.findall(name,cardId,pageindex,pagesize);
    }

    @Override
    public int deletebyid(int parseInt) {
        return queryDao.delete(parseInt);
    }

    @Override
    public RealEstate findbyid(int parseInt) {
        return queryDao.findbyid(parseInt);
    }

    @Override
    public int update(RealEstate realEstate) {
        return queryDao.update(realEstate);
    }

    @Override
    public int add(RealEstate realEstate) {
        return queryDao.add(realEstate);
    }
}
