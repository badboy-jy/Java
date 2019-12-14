package com.alibaba.dao;

import com.alibaba.bean.QueryVo;
import com.alibaba.bean.Topaid;

import java.util.List;

public interface TopaidMapper {
    public void inserttopaid(Topaid topaid);

    public List<Topaid> findtopaid(QueryVo vo);

    public Topaid findbyid(Integer id);

    public void deletetopaid(Integer id);
}
