package com.alibaba.dao;

import com.alibaba.bean.Zulist;

import java.util.List;

public interface ZulistMapper {
    public void insertzulist(Zulist zulist);

    public List<Zulist> findzuuserlist() throws Exception;

    Zulist findzulist(String house_id);

    public void deletezulist(String house_id);

    public List<Zulist> findzulistbyuid(Integer userlist_id);

    public Zulist findzukezulist(Integer id);
}
