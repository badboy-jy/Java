package com.badboyjy.dao;

import com.badboyjy.bean.Bill;
import com.badboyjy.bean.Supplier;

import java.util.List;

public interface BillDao {
    public List<Bill> findallbill(String goodsName, String payStatus, int pageindex, int pagesize);

    public int totalcount(String goodsName, String payStatus);

    public int check(Supplier supplier);

    public int addbill(Bill bill);

    public int deletebyid(int parseInt);

    public Bill findbyid(int parseInt);

    public int update(Bill bill);
}
