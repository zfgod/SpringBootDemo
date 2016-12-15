package com.example.study.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sys.model.AmqObject;

/**
 * author: zf
 * Date: 2016/12/6  18:57
 * Description:
 */
@Controller
@RequestMapping("/msg")
public class MsgController {
    @Autowired
    MsgSend msgSend;

    /**
     * 发送消息到队列
     * Queue队列：仅有一个订阅者会收到消息，消息一旦被处理就不会存在队列中
     * @param message
     * @return String
     */
    @ResponseBody
    @RequestMapping("/queueSender")
    public String queueSender(@RequestParam("message")String message){
        String opt="";
        try {
            msgSend.sendToQueue("test.queue", message);
            opt = "suc";
        } catch (Exception e) {

            opt = e.getCause().toString();
        }
        return opt;
    }

    @ResponseBody
    @RequestMapping("/queueObjectSender")
    public String queueObjectSender(@RequestParam("name")String name,
                                    @RequestParam("message")String message){
        String opt="";
        try {
            AmqObject o = new AmqObject();
            o.setName(name);
            o.setMsg(message);
            msgSend.sendObjectToQueue(name,o);
            opt = "suc";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opt;
    }

    /**
     * 发送消息到主题
     * Topic主题 ：放入一个消息，所有订阅者都会收到
     * 这个是主题目的地是一对多的
     * @param message
     * @return String
     */
    @ResponseBody
    @RequestMapping("/topicSender")
    public String topicSender(@RequestParam("message")String message){
        String opt = "";
        try {
            msgSend.sendToTopic("test.topic", message);
            opt = "suc";
        } catch (Exception e) {
            opt = e.getCause().toString();
        }
        return opt;
    }

    @ResponseBody
    @RequestMapping("/topicObjectSender")
    public String topicObjectSender(@RequestParam("name")String name,
                                    @RequestParam("message")String message){
        String opt="";
        try {
            AmqObject o = new AmqObject();
            o.setName(name);
            o.setMsg(message);
            msgSend.sendObjectToTopic(name,o);
            opt = "suc";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opt;
    }

}
