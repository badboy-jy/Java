package com.badboyjy.service.impl;

import com.badboyjy.bean.EBookCategory;
import com.badboyjy.dao.EBookCategoryDao;
import com.badboyjy.dao.impl.EBookCategoryDaoImpl;
import com.badboyjy.service.EBookCategoryService;

import java.util.List;

public class EBookCategoryServiceImpl implements EBookCategoryService {
    private EBookCategoryDao  categoryDao = (EBookCategoryDao) new EBookCategoryDaoImpl();
    @Override
    public List<EBookCategory> findtypes() {
        return categoryDao.findtypes();
    }
}
