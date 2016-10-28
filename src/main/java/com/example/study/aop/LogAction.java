package com.example.study.aop;

import java.lang.annotation.*;

/**
 * author: zf
 * Date: 2016/10/28  11:13
 * Description: 拦截规则的注解
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAction {
    String name();
}
