package com.badboyjy.dao.impl;

import com.badboyjy.bean.Supplier;
import com.badboyjy.utils.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl extends DbUtils implements com.badboyjy.dao.SupplierDao {
    @Override
    public List<Supplier> findallsupplier(String supplierName, String supplierDesc, int pageindex, int pagesize) {
        List<Supplier> list = new ArrayList<Supplier>();
        StringBuffer sql =
                new StringBuffer("select * from supplier where 1=1");
        List list1 = new ArrayList();
        //接参数数据  判断参数  通过判断拼接sql语句   填充参数到占位符
        if (!supplierName.equals("") && !supplierName.equals("全部")) {
            sql.append(" and supplierName like ?  "); //前后一定要加空格
            list1.add("%" + supplierName + "%");
        }
        if (!supplierDesc.equals("") && !supplierDesc.equals("全部")) {
            sql.append(" and supplierDesc like ?  "); //前后一定要加空格
            list1.add("%" + supplierDesc + "%");
        }
        sql.append(" limit ?,?");

        list1.add((pageindex - 1) * pagesize);
        list1.add(pagesize);
        ResultSet resultSet = query(sql.toString(), list1);
        try {
            while (resultSet.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplierId(resultSet.getInt("supplier_id"));
                supplier.setSupplierName(resultSet.getString("supplierName"));
                supplier.setSupplierDesc(resultSet.getString("supplierDesc"));
                supplier.setSupplierPerName(resultSet.getString("supplierPerName"));
                supplier.setSupplierPhone(resultSet.getString("supplierPhone"));
                supplier.setSupplierChuanZhen(resultSet.getString("supplierChuanZhen"));
                supplier.setSupplierAddress(resultSet.getString("supplierAddress"));
                list.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return list;
    }

    @Override
    public int totalcount(String supplierName, String supplierDesc) {
        int count = 0;
        try {
            StringBuffer sql =
                    new StringBuffer("select count(*) from supplier  where 1=1");
            List list = new ArrayList();
            //接参数数据  判断参数  通过判断拼接sql语句   填充参数到占位符
            if (!supplierName.equals("") && !supplierName.equals("全部")) {
                sql.append(" and supplierName like ?  "); //前后一定要加空格
                list.add("%" + supplierName + "%");
            }
            if (!supplierDesc.equals("") && !supplierDesc.equals("全部")) {
                sql.append(" and supplierDesc like ?  "); //前后一定要加空格
                list.add("%" + supplierDesc + "%");
            }

            ResultSet resultSet = query(sql.toString(), list);
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return count;
    }

    @Override
    public int addsupplier(Supplier supplier) {

        int i = 0;
        try {
            String sql = "insert into supplier values(null,?,?,?,?,?,?)";
            List list = new ArrayList();
            list.add(supplier.getSupplierName());
            list.add(supplier.getSupplierDesc());
            list.add(supplier.getSupplierPerName());
            list.add(supplier.getSupplierPhone());
            list.add(supplier.getSupplierChuanZhen());
            list.add(supplier.getSupplierAddress());
            i = update(sql, list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeall();
        }

        return i;
    }

    @Override
    public int deletebyid(int parseInt) {
        int update = 0;
        String sql = "delete from supplier where supplier_id =?";
        List list = new ArrayList();
        list.add(parseInt);
        update = update(sql, list);
        return update;
    }

    @Override
    public Supplier findbyid(int parseInt) {
        Supplier supplier = new Supplier();
        try {
            String sql = "select *  from supplier where supplier_id =?";
            List list = new ArrayList();
            list.add(parseInt);
            ResultSet resultSet = query(sql, list);
            while (resultSet.next()) {
                supplier.setSupplierId(resultSet.getInt("supplier_id"));
                supplier.setSupplierName(resultSet.getString("supplierName"));
                supplier.setSupplierDesc(resultSet.getString("supplierDesc"));
                supplier.setSupplierPerName(resultSet.getString("supplierPerName"));
                supplier.setSupplierPhone(resultSet.getString("supplierPhone"));
                supplier.setSupplierChuanZhen(resultSet.getString("supplierChuanZhen"));
                supplier.setSupplierAddress(resultSet.getString("supplierAddress"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return supplier;
    }

    @Override
    public int update(Supplier supplier) {
        List list= new ArrayList();
        int update = 0;
        try {
            String sql = "update supplier set supplierName=?,supplierDesc=?,supplierPerName=?,supplierPhone=?,supplierChuanZhen=?,supplierAddress=? where supplier_id=?";
            list.add(supplier.getSupplierName());
            list.add(supplier.getSupplierDesc());
            list.add(supplier.getSupplierPerName());
            list.add(supplier.getSupplierPhone());
            list.add(supplier.getSupplierChuanZhen());
            list.add(supplier.getSupplierAddress());
            list.add(supplier.getSupplierId());
            update = update(sql, list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return update;
    }

}
