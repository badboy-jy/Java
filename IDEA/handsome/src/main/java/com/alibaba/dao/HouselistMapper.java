package com.alibaba.dao;

import com.alibaba.bean.Houselist;
import com.alibaba.bean.QueryVo;

import java.util.List;

public interface HouselistMapper {
    List<Houselist> selectAll();

    public Integer findhouselistByVoCount(QueryVo vo);

    Houselist findhouseid(String houseid);

    void inserthouse(Houselist houselist);

    void deletehouse(int id);

    Houselist findid(int id);

    Houselist findhouseidupdate(Houselist houselist);

    void updatehouse(Houselist houselist);

    void updatehousestatus(Houselist houselist);

    public void deletehousebyhouseid(String house_id);

    public void updatestatus(Houselist houselist);
}
