package com.badboyjy.service;

import com.badboyjy.bean.EBookEntry;

import java.util.List;

public interface EBookEntryService {
    // 接口 定义方法  连接servlet需求和 dao层方法
    public List<EBookEntry> findall(String query1,String query2, int pageindex, int pagesize);
    public int addBook(EBookEntry entry);
    public EBookEntry findbyid(int id);
    public int updateBook(EBookEntry entry);
    public int deletebyid(int parseInt);
    public int totalcount(String query1,String query2);
}
