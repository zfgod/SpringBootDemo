//package com.example.common.cacheCustomer;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CachingConfigurer;
//import org.springframework.cache.ehcache.EhCacheCacheManager;
//import org.springframework.cache.interceptor.*;
//
///**
// * author: zf
// * Date: 2016/11/8  17:58
// * Description:
// *  不需要。。
// */
////@CacheConfig
//public class MyCacheConfig implements CachingConfigurer {
//
//    @Override
//    public CacheManager cacheManager() {
//        return new EhCacheCacheManager();
//    }
//
//    @Override
//    public CacheResolver cacheResolver() {
//        return new SimpleCacheResolver();
//    }
//
//    @Override
//    public KeyGenerator keyGenerator() {
//        return new SimpleKeyGenerator();
//    }
//
//    @Override
//    public CacheErrorHandler errorHandler() {
//        return new SimpleCacheErrorHandler();
//    }
//}
