package com.alibaba.service;

import com.alibaba.bean.Houselist;

import java.util.List;

public interface HouselistService {
    List<Houselist> selectAll();

    Houselist findhouseid(String houseid);

    void inserthouse(Houselist houselist);

    void deletehouse(int id);

    Houselist findid(int id);

    Houselist findhouseidupdate(Houselist houselist);

    void updatehouse(Houselist houselist);

    void updatehousestatus(Houselist houselist);

    public void deletehousebyhouseid(String house_id);
}
