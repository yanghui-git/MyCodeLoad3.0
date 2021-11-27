package com.yh.mongo.test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.yh.mongo.MongoConfig;
import org.junit.Test;

import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class MongoTest {

    MongoConfig mongoConfig = new MongoConfig();

    /**
     * 增
     *
     * @throws UnknownHostException
     */
    @Test
    public void test() throws UnknownHostException {
        DBCollection collection = mongoConfig.getDb().getCollection("yh_test_1");
        for (int i = 0; i < 10; i++) {
            collection.insert(new BasicDBObject("name", "mongo").append("age", i));
        }
    }

    /**
     * 查
     * https://www.jb51.net/article/83278.htm
     *
     * @throws UnknownHostException
     */
    @Test
    public void select() throws UnknownHostException {
        DBCollection collection = mongoConfig.getDb().getCollection("yh_test_1");
        System.out.println("查询第一个: ");
        System.out.println(collection.findOne());
        System.out.println("查询所有: ");
        //查询所有
        DBCursor dbObjects = collection.find();
        try {
            while (dbObjects.hasNext()) {
                System.out.println(dbObjects.next());
            }
        } finally {
            dbObjects.close();
        }
        System.out.println("条件查询");
        //条件查询
        BasicDBObject query = new BasicDBObject("age", new BasicDBObject("$gte", 8));
        dbObjects = collection.find(query);
        try {
            while (dbObjects.hasNext()) {
                System.out.println(dbObjects.next());
            }
        } finally {
            dbObjects.close();
        }
        //模糊匹配
        //db.yh_test_1.find({"name":{ $regex:/mon/ }})
        System.out.println("模糊匹配:");
        Pattern pattern = Pattern.compile("^.*mon.*$", Pattern.CASE_INSENSITIVE);
        query = new BasicDBObject();
        query.put("name", pattern);
        dbObjects = collection.find(query);
        try {
            while (dbObjects.hasNext()) {
                System.out.println(dbObjects.next());
            }
        } finally {
            dbObjects.close();
        }

    }

    /**
     * 删
     */
    @Test
    public void delete() throws UnknownHostException {
        DBCollection collection = mongoConfig.getDb().getCollection("yh_test_1");
        BasicDBObject query = new BasicDBObject("age", 9);
        collection.remove(query);
    }

    /**
     * 改
     */
    @Test
    public void update() throws UnknownHostException {
        DBCollection collection = mongoConfig.getDb().getCollection("yh_test_1");
        BasicDBObject query = new BasicDBObject("age", 8);
        BasicDBObject up = new BasicDBObject("$set", new BasicDBObject("age", 8888));
        collection.update(query, up);
    }

}
