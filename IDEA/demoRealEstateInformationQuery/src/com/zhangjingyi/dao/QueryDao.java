package com.zhangjingyi.dao;

import com.zhangjingyi.bean.RealEstate;

import java.util.List;

public interface QueryDao {
    public int totalcount(String name, String cardId);

    public List<RealEstate> findall(String name, String cardId, int pageindex, int pagesize);

    public int delete(int parseInt);

    public RealEstate findbyid(int parseInt);

    public int update(RealEstate realEstate);

    public int add(RealEstate realEstate);
}
