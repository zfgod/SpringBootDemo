package com.example.study.service;

import org.springframework.stereotype.Service;

/**
 * author: zf
 * Date: 2016/10/28  10:27
 * Description:
 */
@Service
public class DemoService {

    public String say(String s){
        return "javaConfigBean-toSay"+s;
    }
}
