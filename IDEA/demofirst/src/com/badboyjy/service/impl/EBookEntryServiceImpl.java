package com.badboyjy.service.impl;


import com.badboyjy.bean.EBookEntry;
import com.badboyjy.dao.EBookEntryDao;
import com.badboyjy.dao.impl.EBookEntryDaoImpl;
import com.badboyjy.service.EBookEntryService;

import java.util.List;

public class EBookEntryServiceImpl  implements EBookEntryService {
    private EBookEntryDao dao=new EBookEntryDaoImpl();
    @Override
    public List<EBookEntry> findall() {
        return dao.findall();
    }

    @Override
    public int addBook(EBookEntry entry) {
        return dao.addBook(entry);
    }

    @Override
    public EBookEntry findbyid(int id) {
        return dao.findbyid(id);
    }

    @Override
    public int updateBook(EBookEntry entry) {
        return dao.updateBook(entry);
    }

    @Override
    public int deletebyid(int parseInt) {
        return dao.deletebyid(parseInt);
    }

    @Override
    public List<EBookEntry> fuzzyquery(String query) {
        return dao.fuzzyquery(query);
    }

}
