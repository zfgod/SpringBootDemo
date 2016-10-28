package com.example.study.config;

import com.example.study.service.UseDemoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author: zf
 * Date: 2016/10/28  10:27
 * Description:
 */
public class JavaConfigRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoJavaConfig.class);
        UseDemoService useDemoService = context.getBean(UseDemoService.class);
        String hello = useDemoService.say("hello");
        System.out.println(hello);
        context.close();
    }
}
