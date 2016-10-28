package com.example.study.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * author: zf
 * Date: 2016/10/28  11:31
 * Description: Spring AspectJ代理支持 配置类
 *  在SpringBoot 使用spring.aop.auto=true 代替此配置类
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy //开启Spring对AspectJ代理的支持
public class AopConfig {

}
