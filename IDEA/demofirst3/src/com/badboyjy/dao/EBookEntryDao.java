package com.badboyjy.dao;

import com.badboyjy.bean.EBookEntry;

import java.util.List;

public interface EBookEntryDao {

    //按 title字段名模糊查  type.name 模糊查  分页查  条件查询所有     select
    public List<EBookEntry> findall(String query1,String query2, int pageindex, int pagesize);

    //添加   update
    public int addBook(EBookEntry entry);

    //通过id 查  查唯一 select
    public EBookEntry findbyid(int id);

    //修改   update
    public int updateBook(EBookEntry entry);

    //通过id删除  删唯一   delete
   public int deletebyid(int parseInt);

   //通过条件 查记录总数 count
    public int totalcount(String query1,String query2);


}
