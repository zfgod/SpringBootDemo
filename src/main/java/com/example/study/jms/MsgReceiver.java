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
    @JmsListener(destination = "test-destination")
    public void receiveMessage(String msg){
        System.out.println("接收消息："+msg);
    }
}
