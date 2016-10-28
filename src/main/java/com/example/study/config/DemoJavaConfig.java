package com.example.study.config;

import com.example.study.service.DemoService;
import com.example.study.service.UseDemoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author: zf
 * Date: 2016/10/28  10:29
 * Description:
 */
@Configuration//声明一个配置类，不使用包扫描，
public class DemoJavaConfig {

    @Bean//声明当前方法返回的是一个Bean
    public DemoService demoService(){
        return new DemoService();
    }

    @Bean//声明当前方法返回的是一个Bean
    public UseDemoService userDemoService(){
        UseDemoService useDemoService = new UseDemoService();
        useDemoService.setDemoService(demoService());
        return useDemoService;
    }

}
