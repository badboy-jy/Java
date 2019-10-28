package com.badboyjy.service;

import com.badboyjy.bean.EBookEntry;

import java.util.List;

public interface EBookEntryService {
    public List<EBookEntry> findall();
    public int addBook(EBookEntry entry);
    public EBookEntry findbyid(int id);
    public int updateBook(EBookEntry entry);

   public int deletebyid(int parseInt);

   public List<EBookEntry> fuzzyquery(String query);
}
