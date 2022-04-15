package com.mytest.starter.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟RedisUtil 服务工具类
 */
public class RedisUtil {

    Map<String, String> map = new HashMap();

    public void set(String key, String value) {
        this.map.put(key, value);
    }

    public String get(String key) {
        return this.map.get(key);
    }
}
