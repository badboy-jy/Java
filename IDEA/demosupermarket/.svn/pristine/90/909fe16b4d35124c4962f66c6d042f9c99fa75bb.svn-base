package com.badboyjy.service.impl;

import com.badboyjy.bean.Bill;
import com.badboyjy.bean.Supplier;
import com.badboyjy.dao.BillDao;
import com.badboyjy.dao.impl.BillDaoImpl;
import com.badboyjy.service.BillService;
import com.badboyjy.utils.DbUtils;

import java.util.List;

public class BillServiceImpl extends DbUtils implements BillService {
    BillDao billDao = new BillDaoImpl();

    @Override
    public List<Bill> findallbill(String goodsName, String payStatus, int pageindex, int pagesize) {
        return billDao.findallbill(goodsName,payStatus,pageindex,pagesize);
    }

    @Override
    public int totalcount(String goodsName, String payStatus) {
        return billDao.totalcount(goodsName,payStatus);
    }

    @Override
    public int check(Supplier supplier) {
        return billDao.check(supplier);
    }

    @Override
    public int addbill(Bill bill) {
        return billDao.addbill(bill);
    }

    @Override
    public int deletebyid(int parseInt) {
        return billDao.deletebyid(parseInt);
    }

    @Override
    public Bill findbyid(int parseInt) {
        return billDao.findbyid(parseInt);
    }

    @Override
    public int updatebill(Bill bill) {
        return billDao.update(bill);
    }
}
