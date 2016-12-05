package com.example.study.jms;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * author: zf
 * Date: 2016/12/5  18:58
 * Description:
 */
public class Msg implements MessageCreator {
    @Override
    public Message createMessage(Session session) throws JMSException {

        return session.createTextMessage("测试消息内容发送！");
    }
}
