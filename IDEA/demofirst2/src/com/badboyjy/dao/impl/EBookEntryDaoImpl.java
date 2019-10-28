package com.badboyjy.dao.impl;

import com.badboyjy.bean.EBookCategory;
import com.badboyjy.bean.EBookEntry;
import com.badboyjy.dao.EBookEntryDao;
import com.badboyjy.utils.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EBookEntryDaoImpl extends DbUtils implements EBookEntryDao {
    //实现接口  重写方法
    //按 title字段名模糊查  type.name 模糊查  分页查  条件查询所有     select
    @Override
    public List<EBookEntry> findall(String query1,String query2, int pageindex, int pagesize) {
        List<EBookEntry> list = new ArrayList<EBookEntry>();
//        String sql = "select book.*,type.id tid ,type.name typename from ebook_category type,ebook_entry  book where type.id=book.categoryId and book.title like ? limit ?,? ";
        //动态sql

        StringBuffer sql=
                new StringBuffer("select book.*,type.id tid ,type.name typename from ebook_category type,ebook_entry  book" +
                        " where type.id=book.categoryId");
        List list1 = new ArrayList();
        //接参数数据  判断参数  通过判断拼接sql语句   填充参数到占位符
        if(!query1.equals("")&&!query1.equals("全部")){
            sql.append(" and book.title like ?  "); //前后一定要加空格
            list1.add("%"+query1+"%");
        }
        if(!query2.equals("")&&!query2.equals("全部")){
            sql.append(" and type.name like ?  "); //前后一定要加空格
            list1.add("%"+query2+"%");
        }
        sql.append(" limit ?,?");

        list1.add((pageindex-1)*pagesize);
        list1.add(pagesize);
        ResultSet resultSet = query(sql.toString(), list1);
        try {
            while (resultSet.next()) {
                EBookEntry EBookEntry = new EBookEntry();
                EBookEntry.setId(resultSet.getInt("id"));
                EBookEntry.setTitle(resultSet.getString("title"));
                EBookEntry.setSummary(resultSet.getString("summary"));
                EBookEntry.setUploadUser(resultSet.getString("uploaduser"));
                EBookEntry.setCreatDate(resultSet.getDate("createDate"));


                EBookCategory category = new EBookCategory();
                category.setId(resultSet.getInt("tid"));
                category.setName(resultSet.getString("typename"));

                EBookEntry.setType(category);

                list.add(EBookEntry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return list;
    }

    //添加   update
    @Override
    public int addBook(EBookEntry entry) {
        //1.定义sql
        int update = 0;
        try {
            String sql = "insert into ebook_entry values(null,?,?,?,?,?)";
            ArrayList list = new ArrayList();
            list.add(entry.getCategoryId());
            list.add(entry.getTitle());
            list.add(entry.getSummary());
            list.add(entry.getUploadUser());
            list.add(entry.getCreatDate());
            update = update(sql, list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return update;
    }

    //通过id 查  查唯一 select
    @Override
    public EBookEntry findbyid(int id) {
        EBookEntry eBookEntry = new EBookEntry();
        try {
            String sql = "select *  from ebook_entry where id =?";
            List list = new ArrayList();
            list.add(id);
            ResultSet resultSet = query(sql, list);
            while (resultSet.next()) {
                eBookEntry.setId(resultSet.getInt("id"));
                eBookEntry.setTitle(resultSet.getString("title"));
                eBookEntry.setSummary(resultSet.getString("summary"));
                eBookEntry.setUploadUser(resultSet.getString("uploaduser"));
                eBookEntry.setCreatDate(resultSet.getDate("createDate"));
                eBookEntry.setCategoryId(resultSet.getInt("categoryid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return eBookEntry;
    }

    //修改   update
    @Override
    public int updateBook(EBookEntry entry) {
        int update = 0;
        try {
            String sql = "update ebook_entry set title=?,categoryId=?,summary=?,uploaduser=?,createdate=? where id=?";
            List params = new ArrayList();
            params.add(entry.getTitle());
            params.add(entry.getCategoryId());
            params.add(entry.getSummary());
            params.add(entry.getUploadUser());
            params.add(entry.getCreatDate());
            params.add(entry.getId());

            update = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeall();
        }
        return update;
    }

    //通过id删除  删唯一   delete
    @Override
    public int deletebyid(int parseInt) {
        int update = 0;
      String sql = "delete from ebook_entry where id =?";
        List list = new ArrayList();
        list.add(parseInt);
        update = update(sql, list);
        return update;
    }

    //通过条件 查记录总数 count
    @Override
    public int totalcount(String query1,String query2) {
        int count=0;
        try {
//            String sql="select count(*) from ebook_entry where ebook_entry.title like ? ";
            //动态sql
            StringBuffer sql=
                    new StringBuffer("select count(*) from ebook_category type,ebook_entry  book where type.id=book.categoryId");
            List list = new ArrayList();
            //接参数数据  判断参数  通过判断拼接sql语句   填充参数到占位符
            if(!query1.equals("")&&!query1.equals("全部")){
                sql.append(" and book.title like ?  "); //前后一定要加空格
                list.add("%"+query1+"%");
            }
            if(!query2.equals("")&&!query2.equals("全部")){
                sql.append(" and type.name like ?  "); //前后一定要加空格
                list.add("%"+query2+"%");
            }

            ResultSet resultSet = query(sql.toString(), list);
            while(resultSet.next()){
                //count=query.getInt("count(*)");
                count=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeall();
        }
        return count;
    }


}