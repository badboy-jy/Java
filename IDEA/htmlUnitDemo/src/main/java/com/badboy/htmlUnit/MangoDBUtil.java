package com.badboy.htmlUnit;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

public enum MangoDBUtil {
    instance;
    private MongoClient mongoClient;
    static {
        String ip = "127.0.0.1";
        int port = 27017;
        instance.mongoClient = new MongoClient(ip,port);
    }
    public MongoDatabase getDB (String dbName){
        if (dbName != null && !"".equals(dbName)){
            MongoDatabase database = mongoClient.getDatabase(dbName);
            return database;
        }
        return null;
    }
    public MongoCollection<Document> getCollection(String dbName, String collName){
        if (collName == null || "".equals(collName)){
            return null;
        }
        if (dbName == null || "".equals(dbName)){
            return null;
        }
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collName);
        return collection;
    }
    public MongoCursor<Document> find(MongoCollection<Document> coll, Bson filter) {
        if (filter != null) {
            return coll.find(filter).iterator();
        } else {
            return coll.find().iterator();
        }
    }
    public void insert(MongoCollection<Document> coll,Document doc){
        coll.insertOne(doc);
    }
    public void update(MongoCollection<Document> coll,Document queryDoc,Document updateDoc){
        Document modifiers = new Document();
        modifiers.append("$set",updateDoc);
        coll.updateMany(queryDoc,modifiers);
    }
    public  void delete(MongoCollection<Document> coll,Document doc){
        coll.deleteMany(doc);
    }

}
