package com.badboyjy.dao;

import com.badboyjy.bean.EBookCategory;

import java.util.List;

public interface EBookCategoryDao {

    //查询所有name列  select
    public  List<EBookCategory> findtypes();
}
