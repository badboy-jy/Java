package com.alibaba.dao;

import com.alibaba.bean.Checkout;

import java.util.List;

public interface CheckoutMapper {
    public void insertcheckout(Checkout checkout);

    public List<Checkout> getallcheckout();

    public void deletecheckout(Integer id);
}
