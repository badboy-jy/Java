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
    @Override
    public List<EBookEntry> findall() {
        List<EBookEntry> list = new ArrayList<EBookEntry>();
        String sql = "select book.*,type.id tid ,type.name typename from ebook_category type,ebook_entry  book where type.id=book.categoryId";
        ResultSet resultSet = query(sql, null);
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

    @Override
    public int updateBook(EBookEntry entry) {
        int update = 0;
        try {
            String sql = "update ebook_entry set title=?,summary=?,uploaduser=?,createdate=? where id=?";
            List params = new ArrayList();
            params.add(entry.getTitle());
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

    @Override
    public int deletebyid(int parseInt) {
        int update = 0;
        String sql = "delete from ebook_entry where id =?";
        List list = new ArrayList();
        list.add(parseInt);
        update = update(sql, list);
        return update;
    }

    @Override
    public List<EBookEntry> fuzzyquery(String query) {
        List<EBookEntry> list = new ArrayList<EBookEntry>();
        String sql = "select book.*,type.id tid ,type.name typename from ebook_category type,ebook_entry  book where type.id=book.categoryId and book.title like ? ";
        List list1 = new ArrayList();
        list1.add("%"+query+"%");
        ResultSet resultSet = query(sql, list1);
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

}