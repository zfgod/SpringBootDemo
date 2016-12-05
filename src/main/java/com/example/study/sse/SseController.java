package com.example.study.sse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * author: zf
 * Date: 2016/10/28  17:44
 * Description: 服务器端向客户端推送实例
 */
@Controller
public class SseController {
    @RequestMapping(value = "/goPush1")
    public String goH51(){

        return "stu/push";
    }

    @RequestMapping(value = "/goPush2")
    public String goH52(){
        return "stu/pushTest";
    }
    @RequestMapping(value = "/push",produces = "text/event-stream")
    public @ResponseBody String push(){
        Random r =new Random();
        try {
//          so 这里可以根据需要触发 ,系统有相关操作后进行调用并获取推送数据
            Thread.sleep(5000);//5秒
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("获取推送信息。。");
        return "data:Testing ..."+r.nextInt()+"\n\n";
    }
}
