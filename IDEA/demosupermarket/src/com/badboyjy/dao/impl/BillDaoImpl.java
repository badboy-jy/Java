package com.badboyjy.dao.impl;

import com.badboyjy.bean.Bill;
import com.badboyjy.bean.Supplier;
import com.badboyjy.dao.BillDao;
import com.badboyjy.utils.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl extends DbUtils implements BillDao {
    @Override
    public List<Bill> findallbill(String goodsName, String payStatus, int pageindex, int pagesize) {
        List<Bill> list = new ArrayList<Bill>();
        StringBuffer sql =
                new StringBuffer("select bill.*,supplier.supplier_id,supplier.supplierName from bill,supplier where bill.supplier_id = supplier.supplier_id");
        List list1 = new ArrayList();
        //接参数数据  判断参数  通过判断拼接sql语句   填充参数到占位符
        if (!goodsName.equals("") && !goodsName.equals("全部")) {
            sql.append(" and goodsName like ?  "); //前后一定要加空格
            list1.add("%" + goodsName + "%");
        }
        if (!payStatus.equals("")) {
            sql.append(" and pay = ?  "); //前后一定要加空格
            list1.add(payStatus);
        }
        sql.append(" limit ?,?");

        list1.add((pageindex - 1) * pagesize);
        list1.add(pagesize);
        ResultSet resultSet = query(sql.toString(), list1);
        try {
            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setBillId(resultSet.getInt("bill_id"));
                bill.setGoodsName(resultSet.getString("goodsName"));
                bill.setGoodsCount(resultSet.getInt("goodsCount"));
                bill.setMoney(resultSet.getInt("money"));
                bill.setPay(resultSet.getInt("pay"));
                bill.setGoodsDescription(resultSet.getString("goodsDescription"));
                bill.setTime(resultSet.getDate("time"));
                Supplier supplier = new Supplier();
                supplier.setSupplierId(resultSet.getInt("supplier_id"));
                supplier.setSupplierName(resultSet.getString("supplierName"));
                bill.setSupplier(supplier);
                list.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return list;
    }

    @Override
    public int totalcount(String goodsName, String payStatus) {

        int count = 0;
        try {
            StringBuffer sql =
                    new StringBuffer("select count(*) from bill  where 1=1");
            List list = new ArrayList();
            //接参数数据  判断参数  通过判断拼接sql语句   填充参数到占位符
            if (!goodsName.equals("") && !goodsName.equals("全部")) {
                sql.append(" and goodsName like ?  "); //前后一定要加空格
                list.add("%" + goodsName + "%");
            }
            if (!payStatus.equals("") && !payStatus.equals("全部")) {
                sql.append(" and pay = ?  "); //前后一定要加空格
                list.add(payStatus);
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
    public int check(Supplier supplier) {
        int i=0;
        String sql = "select * from supplier where supplier_id=?";
        List list = new ArrayList();
        list.add(supplier.getSupplierId());
        ResultSet query = query(sql, list);
        try {
            if (query.next()) {
                i= 1;
            } else {
                i= 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeall();
        }
        return  i;
    }

    @Override
    public int addbill(Bill bill) {
        int i = 0;
        try {
            String sql = "insert into bill values(null,?,?,?,?,?,?,?)";
            List list = new ArrayList();
            list.add(bill.getGoodsName());
            list.add(bill.getGoodsCount());
            list.add(bill.getMoney());
            list.add(bill.getPay());
            list.add(bill.getSupplierId());
            list.add(bill.getGoodsDescription());
            list.add(bill.getTime());
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
        String sql = "delete from bill where bill_id =?";
        List list = new ArrayList();
        list.add(parseInt);
        update = update(sql, list);
        return update;
    }
    @Override
    public Bill findbyid(int parseInt) {
        Bill bill = new Bill();
        try {
            String sql = "select *  from bill where bill_id =?";
            List list = new ArrayList();
            list.add(parseInt);
            ResultSet resultSet = query(sql, list);
            while (resultSet.next()) {
                bill.setSupplierId(resultSet.getInt("supplier_id"));
                bill.setBillId(resultSet.getInt("bill_id"));
                bill.setGoodsName(resultSet.getString("goodsName"));
                bill.setGoodsCount(resultSet.getInt("goodsCount"));
                bill.setMoney(resultSet.getInt("money"));
                bill.setPay(resultSet.getInt("pay"));
                bill.setGoodsDescription(resultSet.getString("goodsDescription"));
                bill.setTime(resultSet.getDate("time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return bill;
    }
    @Override
    public int update(Bill bill) {
        List list= new ArrayList();
        int update = 0;
        try {
            String sql = "update bill set goodsName=?,goodsCount=?,money=?,pay=?,supplier_id=?,goodsDescription=? where bill_id=?";
            list.add(bill.getGoodsName());
            list.add(bill.getGoodsCount());
            list.add(bill.getMoney());
            list.add(bill.getPay());
            list.add(bill.getSupplierId());
            list.add(bill.getGoodsDescription());
            list.add(bill.getBillId());
            update = update(sql, list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return update;
    }
}
