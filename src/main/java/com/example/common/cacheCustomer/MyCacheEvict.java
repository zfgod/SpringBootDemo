package com.example.common.cacheCustomer;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * author: zf
 * Date: 2016/11/8  9:22
 * Description: 自定义  清除缓存  注解
 * 自定义注解学习：http://blog.csdn.net/z69183787/article/details/40377689
 */
@Target({ElementType.METHOD, ElementType.TYPE})
// 注解的作用目标 :可以多个
// TYPE:接口、类、枚举、注解, METHOD:方法上的注解
@Retention(RetentionPolicy.RUNTIME)// class文件和运行时都有，运行时反射获取
@Inherited  //说明子类可以继承父类中的该注解
@Documented //说明该注解将被包含在javadoc中
public @interface MyCacheEvict {
//    设置default 值,则属性为非必填项
    String value();//cacheName
    String key() default "";//key
    String[] keys() default {};//key数组
    String keyRegex() default "";//key 模糊匹配
    boolean allEntries() default false;//是否清除此cache下所有缓存

//
    int second() default 60*60; //秒
    String condition() default ""; //参数条件
    boolean reLoad() default false; //重新载入缓存
    String desc() default ""; //key 的描述
    String method() default "";//重新载入缓存时调用的方法名
}
