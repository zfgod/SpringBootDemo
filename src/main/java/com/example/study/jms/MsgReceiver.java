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
//开启spring.jms.pub-sub-domain=true,系统监听的地址都在 topic模式下
//springBoot 默认spring.jms.pub-sub-domain=false,系统监听的地址都在 queue模式下。
//  监听类型只能2选1
    @JmsListener(destination = "test.queue")
    public void receiveMessage(String msg){

        System.out.println("接收消息："+msg);
    }

    /**
     *
     * @param msg
     */
    @JmsListener(destination = "test.topic")
    public void receiveTopicMessage(String msg)
    {
        System.out.println("接收消息："+msg);
    }
}
