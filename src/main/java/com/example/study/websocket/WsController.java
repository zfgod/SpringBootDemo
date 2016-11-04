/*
package com.example.study.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

*/
/**
 * author: zf
 * Date: 2016/11/1  17:38
 * Description:
 *//*

@Controller
public class WsController {
    @Autowired
    //向浏览器发送消息
    private SimpMessagingTemplate messagingTemplate;

    */
/**
     * 广播式已成功
     * @param message
     * @return
     * @throws Exception
     *//*

    @MessageMapping(value = "/wsWelcome")
    @SendTo("/topic/getResponse")
    public ReturnMessage say(TransMessage message) throws Exception {
        Thread.sleep(5000);
        return new ReturnMessage("hello :"+message.getName());
    }

    */
/**
     *
     * @param principal
     * @param msg
     *
//      未成功！！！个人消息订阅不行，可以发送信息过来
     *//*

    @MessageMapping(value = "/chat")
    public void handleChat(Principal principal,String msg){
        //这里假设就两个人 abc/zf 互相发现消息的情景
        if(principal.getName().equals("abc")){//
            System.out.println("abc发送:"+msg);
            messagingTemplate.convertAndSendToUser("zf",
                    "/queue/notifications",
                    principal.getName()+"-send:"+msg);//
        }else {
            System.out.println("zf发送:"+msg);
            messagingTemplate.convertAndSendToUser(
                    "abc",//消息接收用户
                    "/queue/notifications",//浏览器订阅地址
                    principal.getName()+"-send:"+msg);//消息内容
        }
    }


}
*/
