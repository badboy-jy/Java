package com.alibaba.dao;

import com.alibaba.bean.QueryVo;
import com.alibaba.bean.Wrong;

import java.util.List;

public interface WrongMapper {
    public List<Wrong> findwrong(QueryVo vo);

    public Wrong findbyid(Integer id);

    public void insertwrong(Wrong wrong);

    public void deletewrong(Integer id);
}
