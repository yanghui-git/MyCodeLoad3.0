package com.yh.codis.test;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class CodisTest {

    private RedissonClient redissonClient=getRedisSon();

    public RedissonClient getRedisSon() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://121.199.72.238:6379").
                setDatabase(0);
               // .setPassword("123456");
        //config.setCodec(new SerializationCodec());
        return Redisson.create(config);
    }

    @Test
    public void testOne() {
        RBucket rBucket = redissonClient.getBucket("test-codis-one");
        rBucket.set("888");
        System.out.println(rBucket.get());
    }


    @Test
    public void one() {
        //操作数据
        RMap rMap = redissonClient.getMap("test-codis-map");
        rMap.put("name", "redisson-name");
        rMap.put("age", 22);
        //
        System.out.println(rMap.containsKey("name"));
        System.out.println(rMap.get("age"));
    }

}
