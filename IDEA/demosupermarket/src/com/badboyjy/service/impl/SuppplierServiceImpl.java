package com.badboyjy.service.impl;

import com.badboyjy.bean.Supplier;
import com.badboyjy.dao.SupplierDao;
import com.badboyjy.dao.impl.SupplierDaoImpl;
import com.badboyjy.service.SupplierService;
import com.badboyjy.utils.DbUtils;

import java.util.List;

public class SuppplierServiceImpl extends DbUtils implements SupplierService {
    SupplierDao supplierDao = new SupplierDaoImpl();
    @Override
    public List<Supplier> findallsupplier(String supplierName, String supplierDesc, int pageindex, int pagesize) {
        return supplierDao.findallsupplier(supplierName,supplierDesc,pageindex,pagesize);
    }

    @Override
    public int totalcount(String supplierName, String supplierDesc) {
        return supplierDao.totalcount(supplierName,supplierDesc);
    }

    @Override
    public int addsupplier(Supplier supplier) {
        return supplierDao.addsupplier(supplier);
    }

    @Override
    public int deletebyid(int parseInt) {
        return supplierDao.deletebyid(parseInt);
    }

    @Override
    public Supplier findbyid(int parseInt) {
        return supplierDao.findbyid(parseInt);
    }

    @Override
    public int update(Supplier supplier) {
        return supplierDao.update(supplier);
    }
}
