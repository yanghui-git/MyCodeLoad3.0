package com.yh.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;
import java.util.Objects;

/**
 * Mongo 基础操作
 * https://zhuanlan.zhihu.com/p/32909451
 */
public class MongoConfig {

    private MongoClient mongoClient;

    private DB db;

    public MongoClient getMongoClient() throws UnknownHostException {
        if (Objects.isNull(mongoClient)) {
            mongoClient = new MongoClient("121.199.72.238", 27017);
        }
        return mongoClient;
    }

    public DB getDb() throws UnknownHostException {
        if (Objects.isNull(db)) {
            db = getMongoClient().getDB("mongo_test_yh");
        }
        return db;
    }

}
