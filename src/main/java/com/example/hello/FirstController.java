package com.example.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: zf
 * Date: 2016/10/25  11:45
 * Description:
 */
//@RestController （ @Controller @ResponseBody)
@RestController
public class FirstController {

    @Value("${server.session.timeout}")
    private String sessionTime;

     @RequestMapping(value = "/hello")
    public String index(){
        return "hello zfaa"+"--"+sessionTime;
    }


}
