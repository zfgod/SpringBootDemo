package com.example.study.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author: zf
 * Date: 2016/10/28  11:34
 * Description: 测试aop 注解式、方法规则式
 */
public class Run {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AopConfig.class);
        DemoAnnotationService demoAnnotationService =
                context.getBean(DemoAnnotationService.class);

        DemoMethodService demoMethodService =
                context.getBean(DemoMethodService.class);
        demoAnnotationService.add();
        demoMethodService.add();
        context.close();
    }
}
