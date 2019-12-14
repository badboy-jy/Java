package com.alibaba.service;

import com.alibaba.bean.Paid;
import com.alibaba.bean.QueryVo;
import com.alibaba.bean.Zulist;

import java.util.List;

public interface PaidService {
    public List<Paid> selectall(QueryVo vo);

    public Double selectsum(QueryVo vo);

    public void deletepaid(Integer id);

    public List<Zulist> findzuuserlist() throws Exception;

    public Zulist findzukezulist(Integer id);

}
