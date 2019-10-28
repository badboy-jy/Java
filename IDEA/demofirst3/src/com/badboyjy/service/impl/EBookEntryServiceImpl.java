package com.badboyjy.service.impl;


import com.badboyjy.bean.EBookEntry;
import com.badboyjy.dao.EBookEntryDao;
import com.badboyjy.dao.impl.EBookEntryDaoImpl;
import com.badboyjy.service.EBookEntryService;

import java.util.List;

public class EBookEntryServiceImpl  implements EBookEntryService {
    // 实现接口方法  连接servlet需求和 dao层方法
    private EBookEntryDao dao=new EBookEntryDaoImpl();
    @Override
    public List<EBookEntry> findall(String query1,String query2, int pageindex, int pagesize) {
        return dao.findall(query1,query2,pageindex,pagesize);
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
    public int totalcount(String query1,String query2) {
        return dao.totalcount(query1,query2);
    }


}
