package com.example.common.cacheCustomer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * author: zf
 * Date: 2016/11/8  18:51
 * Description:
 */
public class ParameterMap {
    private static ConcurrentHashMap<String, String[]> map = new ConcurrentHashMap<String, String[]>();

    public static void put(String key, String[] paramNames) {
        map.putIfAbsent(key, paramNames);
    }

    public static String[] get(String key) {
        return map.get(key);
    }
}
