package com.alibaba.dao;

import com.alibaba.bean.Paid;
import com.alibaba.bean.QueryVo;

import java.util.List;

public interface PaidMapper {
    public List<Paid> selectall(QueryVo vo);

    public Double selectsum(QueryVo vo);

    public void deletepaid(Integer id);

    public void insertpaid(Paid paid);
}
