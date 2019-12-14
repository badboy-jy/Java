package com.alibaba.service;

import com.alibaba.bean.Zulist;

import java.util.List;

public interface ZulistService {
    public void insertzulist(Zulist zulist);

    public List<Zulist> findzuuserlist() throws Exception;

    public Zulist findzulist(String house_id);

    public void deletezulist(String house_id);

    public List<Zulist> findzulistbyuid(Integer userlist_id);
}
