package com.example.hello;

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
     @RequestMapping(value = "/hello")
    public String index(){
        return "hello zf";
    }


}
