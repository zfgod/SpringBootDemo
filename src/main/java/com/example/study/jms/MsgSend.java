//package com.example.study.jms;
//
//import org.apache.activemq.command.ActiveMQQueue;
//import org.apache.activemq.command.ActiveMQTopic;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.core.MessageCreator;
//import org.springframework.stereotype.Component;
//import sys.model.AmqObject;
//
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.ObjectMessage;
//import javax.jms.Session;
//
///**
// * author: zf
// * Date: 2016/12/6  18:59
// * Description:
// */
//
//@Component("msgSend")
//public class MsgSend {
////        jmsMessagingTemplate.convertAndSend(D destination, Object payload)
////        ActiveMQTopic、ActiveMQQueue  extends Destination
////        Supported message payloads are: String, byte array, Map, Serializable object.
////        发送消息可以根据指定发送的消息模式(queue/topic)
////                      ,目的地(destination):ActiveMQQueue 为queue模式,ActiveMQTopic 为topic模式
////                      消息体内容(Message)
//
///**
//     * 注入JmsMessagingTemplate
//     */
//
//    @Autowired
//    JmsMessagingTemplate jmsMessagingTemplate;
//
//    @Autowired
//    private JmsTemplate jmsTemplate;
///**
//     * 向指定的点对点队列发送消息
//     * @param queueName 队列名称
//     * @param message 消息内容
//     */
//
//    public void sendToQueue(String queueName,final String message){
//        ActiveMQQueue activeMQQueue = new ActiveMQQueue(queueName);//继承了父类Destination
//        jmsMessagingTemplate.convertAndSend(activeMQQueue,message);
//    }
//
//    public void sendObjectToQueue(String queueName,AmqObject message){
//        ActiveMQQueue activeMQQueue = new ActiveMQQueue(queueName);//继承了父类Destination
//        jmsTemplate.send(activeMQQueue, new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                return session.createObjectMessage(message);
//            }
//        });
//    }
//
//
///**
//     * 向指定的主题队列发送消息
//     * @param queueName  队列名称
//     * @param message  消息内容
//     */
//
//    public void sendToTopic(String queueName,final String message){
//        ActiveMQTopic activeMQTopic = new ActiveMQTopic(queueName);//继承了父类Destination
//        jmsMessagingTemplate.convertAndSend(activeMQTopic,message);
//    }
//
//    public void sendObjectToTopic(String queueName,AmqObject message){
//        jmsTemplate.send(new ActiveMQTopic(queueName), new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                ObjectMessage objectMessage = session.createObjectMessage(message);
//                objectMessage.setObject(message);
//                String s = objectMessage.toString();
//                System.out.println(s);
//                return  objectMessage;
//            }
//        });
//    }
//
//}
//
