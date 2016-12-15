package com.example.study.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import sys.model.AmqObject;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import java.io.Serializable;
import java.util.Enumeration;

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


    @JmsListener(destination = "topic.msg.object2",concurrency = "1")
    public void receiveObject(Message message) throws JMSException {
       try {
           if(message instanceof ObjectMessage){
               Serializable serializable = ((ObjectMessage) message).getObject();
               System.out.println(serializable.toString());
               AmqObject object = (AmqObject) serializable;
               System.out.println(object.toString());
           }else {
               System.out.println(message.toString());
           }
       } catch (Exception e){
         e.printStackTrace();
       }
    }

    @JmsListener(destination = "topic.msg.map",concurrency = "1")
    public void receiveMap(Message message) throws JMSException {
        try {
            if(message instanceof MapMessage){
                MapMessage mapMessage = (MapMessage) message;
                Enumeration mapNames = mapMessage.getMapNames();
                while (mapNames.hasMoreElements()){
                    String s = mapNames.nextElement().toString();
                    System.out.println(s);
                    Object object = mapMessage.getObject(s);
                    Object objectProperty = mapMessage.getObjectProperty(s);
                }
            }else {
                System.out.println(message.toString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
