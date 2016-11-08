package com.example.study.aop;

import org.springframework.stereotype.Service;

/**
 * author: zf
 * Date: 2016/10/28  11:16
 * Description: 注解式 测试书写日志类
 */
@Service
public class DemoAnnotationService {
    @LogAction(name = "DemoAnnotationService的add方法",key="d")
    public void add(){

    }
}
