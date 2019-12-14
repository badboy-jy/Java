package com.alibaba.service;

import com.alibaba.bean.Paid;
import com.alibaba.bean.QueryVo;
import com.alibaba.bean.Topaid;

import java.util.List;

public interface TopaidService {
    public void inserttopaid(Topaid topaid);

    public List<Topaid> findtopaid(QueryVo vo);

    public Topaid findbyid(Integer id);

    public void gotopay(Integer id, Paid paid);
}
