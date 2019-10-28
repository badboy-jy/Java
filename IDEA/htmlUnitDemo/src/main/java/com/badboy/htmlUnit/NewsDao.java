package com.badboy.htmlUnit;

import com.alibaba.fastjson.JSON;
import com.badboy.enity.News;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsDao {
    String dbName = "test";
    String collName = "analysis";
    public void  insert(News news){
        MongoCollection<Document> coll = MangoDBUtil.instance.getCollection(dbName,collName);
        Document doc = new Document();
        doc.put("title",news.getTitle());
        doc.put("content",news.getContent());
        doc.put("url",news.getUrl());
        doc.put("keywords",news.getKeyWords());
        //将评论集合转换为json字符串
        String jsonString = JSON.toJSONString(news.getComment());
        doc.put("comment",jsonString);
        MangoDBUtil.instance.insert(coll,doc);
        System.out.println("insert ING-------------");
    }
    public void delete(Document document){
        MongoCollection<Document> coll = MangoDBUtil.instance.getCollection(dbName,collName);
        MangoDBUtil.instance.delete(coll,document);
        System.out.println("delete ING ---------");

    }
    public List<News> query(Document cond){
        List<News> list = new ArrayList<News>();
        MongoCollection<Document> coll = MangoDBUtil.instance.getCollection(dbName,collName);
        MongoCursor<Document> docs = MangoDBUtil.instance.find(coll,cond);
        while (docs.hasNext()){
            Document c = docs.next();
            News n = new News();
            n.setTitle(c.getString("title"));
            n.setContent(c.getString("content"));
            String jsonString = c.getString("comment");
            List<Comment> coments = JSON.parseArray(jsonString,Comment.class);
            n.setComment(coments);
            list.add(n);
        }
        return list;
    }
}
