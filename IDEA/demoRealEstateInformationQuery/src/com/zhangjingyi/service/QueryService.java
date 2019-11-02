package com.zhangjingyi.service;

import com.zhangjingyi.bean.RealEstate;

import java.util.List;

public interface QueryService {
    public int totalcount(String name, String cardId);

    public List<RealEstate> findall(String name, String cardId, int pageindex, int pagesize);

    public int deletebyid(int parseInt);

    public RealEstate findbyid(int parseInt);

    public int update(RealEstate realEstate);

    public int add(RealEstate realEstate);
}
