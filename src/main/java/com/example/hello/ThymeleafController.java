package com.example.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * author: zf
 * Date: 2016/10/27  18:59
 * Description:
 */
@Controller
public class ThymeleafController {
    @Value("${server.session.timeout}")
    private String sessionTime;
    @RequestMapping("/helloFtl")
    public String helloFtl(Map<String,Object> map){
        map.put("hello","first thymeleaf");
        map.put("sessionTime",sessionTime);
        return"/hello";
    }
}
