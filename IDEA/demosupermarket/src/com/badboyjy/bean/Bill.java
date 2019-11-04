package com.badboyjy.bean;

import java.util.Date;

public class Bill {
    private Integer billId;//账单编号
    private String goodsName;//商品名称
    private Integer goodsCount;//商品数量
    private Integer money;//交易金额
    private Integer pay;//是否付款
    private Integer supplierId;//供应商id
    private String goodsDescription;//商品描述
    private Date time;//交易时间
    private Supplier supplier;

    public Bill(Integer billId, String goodsName, Integer goodsCount, Integer money, Integer pay, Integer supplierId, String goodsDescription, Date time, Supplier supplier) {
        this.billId = billId;
        this.goodsName = goodsName;
        this.goodsCount = goodsCount;
        this.money = money;
        this.pay = pay;
        this.supplierId = supplierId;
        this.goodsDescription = goodsDescription;
        this.time = time;
        this.supplier = supplier;
    }

    public Bill() {
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsCount=" + goodsCount +
                ", money=" + money +
                ", pay=" + pay +
                ", supplierId=" + supplierId +
                ", goodsDescription='" + goodsDescription + '\'' +
                ", time=" + time +
                ", supplier=" + supplier +
                '}';
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
