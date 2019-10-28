package com.badboyjy.service;

import com.badboyjy.bean.EBookCategory;

import java.util.List;

public interface EBookCategoryService {
    // 接口 定义方法  连接servlet需求和 dao层方法
    public List<EBookCategory> findtypes();
}
