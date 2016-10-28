package com.example.study.aware;

import com.example.DemoApplication;
import com.example.study.config.DemoJavaConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author: zf
 * Date: 2016/10/28  16:34
 * Description:
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AwareConfig.class);
        AwareDemoService bean = context.getBean(AwareDemoService.class);
        bean.outputResult();
        context.close();
    }
}
