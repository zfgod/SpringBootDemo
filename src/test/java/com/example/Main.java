package com.example;

import com.example.service.UserService;
import com.example.study.config.DemoJavaConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author: zf
 * Date: 2016/11/4  11:45
 * Description:
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoJavaConfig.class);
    }

}
