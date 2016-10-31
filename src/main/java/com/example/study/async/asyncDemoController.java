package com.example.study.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

/**
 * author: zf
 * Date: 2016/10/31  16:40
 * Description: 异步请求 controller形式
 *
 */
@Controller
public class asyncDemoController {

    @RequestMapping(value = "/async")
    public String goH51(){
        return "/stu/async";
    }


    @RequestMapping(value = "/async/test")
    @ResponseBody
    public Callable<String> callable() {
        // 这么做的好处避免web server的连接池被长期占用而引起性能问题，
        // 调用后生成一个非web的服务线程来处理，增加web服务器的吞吐量。
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3 * 1000L);
                return "异步处理返回- - -时间： " + System.currentTimeMillis();
            }
        };
    }
}
