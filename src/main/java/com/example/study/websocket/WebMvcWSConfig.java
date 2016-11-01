package com.example.study.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * author: zf
 * Date: 2016/11/1  18:20
 * Description: 这里只配置一个viewController 支持webSocket 快捷映射
 */
@Configuration
public class WebMvcWSConfig  extends WebMvcConfigurerAdapter{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //增加一个 测试webSocket的快捷映射
        //相当于在controller中写一个路径为/ws 的方法,返回此页面视图
        registry.addViewController("/ws").setViewName("/stu/ws");//templates/stu/ws.html
    }

}
