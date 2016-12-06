package com.example.study.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * author: zf
 * Date: 2016/12/5  19:09
 * Description:
 */
@Component
public class MsgReceiver {
    @JmsListener(destination = "test.queue")
    public void receiveMessage(String msg){

        System.out.println("接收queue消息："+msg);
    }

    @JmsListener(destination = "test.topic")
    public void receiveTopicMessage(String msg){
        System.out.println("接收topic消息："+msg);
    }
}
