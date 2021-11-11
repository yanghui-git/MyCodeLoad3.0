package com.yh.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yh.fastjson.entity.Apple;
import com.yh.fastjson.entity.Store;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * ASM入门篇
 * https://blog.csdn.net/kshzhaohui/article/details/117807777
 *
 * ASM在FastJson中的应用
 * https://blog.csdn.net/kshzhaohui/article/details/117981754
 *
 * fastjson到底做错了什么？为什么会被频繁爆出漏洞？
 * https://zhuanlan.zhihu.com/p/157211675
 */
public class FastJsonCode {

    /**
     * (fastjson v 1.2.68)
     */
    @Test
    public void testOne() {
        Store store = new Store();
        store.setName("Hollis");
        Apple apple = new Apple();
        apple.setPrice(new BigDecimal(0.5));
        store.setFruit(apple);
        String jsonString = JSON.toJSONString(store);
        System.out.println("toJSONString : " + jsonString);

        // 尝试反序列化回来
        //toJSONString : {"fruit":{"price":0.5},"name":"Hollis"}
        //parseObject : Store(name=Hollis, fruit={})
        //当一个类中包含了一个接口（或抽象类）的时候，在使用fastjson进行序列化的时候，会将子类型抹去，只保留接口（抽象类）的类型，使得反序列化时无法拿到原始类型。
        Store newStore = JSON.parseObject(jsonString, Store.class);
        System.out.println("parseObject : " + newStore);
        Apple newApple = (Apple) newStore.getFruit();
        System.out.println("getFruit : " + newApple);
    }


    /**
     * (fastjson v 1.2.68)
     * 优化修改
     */
    @Test
    public void testTwo() {
        Store store = new Store();
        store.setName("Hollis");
        Apple apple = new Apple();
        apple.setPrice(new BigDecimal(0.5));
        store.setFruit(apple);
        String jsonString = JSON.toJSONString(store, SerializerFeature.WriteClassName);
        System.out.println("toJSONString : " + jsonString);
        //可以看到，使用SerializerFeature.WriteClassName进行标记后，JSON字符串中多出了一个@type字段，
        //标注了类对应的原始类型，方便在反序列化的时候定位到具体类型
        //如上，将序列化后的字符串在反序列化，既可以顺利的拿到一个Apple类型，整体输出内容：
        //反序列化
        /**
         * AutoType 何错之有？
         * 因为有了autoType功能，那么fastjson在对JSON字符串进行反序列化的时候，就会读取@type到内容，试图把JSON内容反序列化成这个对象，并且会调用这个类的setter方法。
         *
         * 那么就可以利用这个特性，自己构造一个JSON字符串，并且使用@type指定一个自己想要使用的攻击类库。
         */
        Store newStore = JSON.parseObject(jsonString, Store.class);
        System.out.println("parseObject : " + newStore);
        Apple newApple = (Apple) newStore.getFruit();
        System.out.println("getFruit : " + newApple);
    }

}
