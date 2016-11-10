package com.example.common.cacheCustomer;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import java.util.List;

/**
 * author: zf
 * Date: 2016/11/9  15:40
 * Description: 通过annotation注入参数属性，实现自定义缓存策略
 */
public class CacheUtils {

//  多个缓存的清除
    public static void removeKeys(List<String> keys,String cacheName,CacheManager cacheManager){
        Cache cache = cacheManager.getCache(cacheName);
        for (String key : keys) {
            cache.remove(key);
        }
    }
//   匹配式缓存的清除
    public static void removeKeysWithRegex(String regex,String cacheName,CacheManager cacheManager){
        Cache cache = cacheManager.getCache(cacheName);
        List keys = cache.getKeys();
//      我这里并没有做正则匹配,按照key为string 然后startsWith去做的
        for (Object key : keys) {
            if(key instanceof  String && ((String) key).trim().startsWith(regex)){
               cache.remove(key);
            }
        }
    }
}
