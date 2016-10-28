package com.example.study.service;

/**
 * author: zf
 * Date: 2016/10/28  10:34
 * Description:
 */
public class UseDemoService {
    DemoService demoService;
    public void setDemoService(DemoService demoService){
        this.demoService = demoService;
    }

    public String say(String s){
        return demoService.say(s);
    }
}
