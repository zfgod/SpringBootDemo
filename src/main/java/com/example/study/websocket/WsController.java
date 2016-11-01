package com.example.study.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * author: zf
 * Date: 2016/11/1  17:38
 * Description:
 */
@Controller
public class WsController {

    @MessageMapping(value = "/wsWelcome")
    @SendTo("/topic/getResponse")
    public ReturnMessage say(TransMessage message) throws Exception {
        Thread.sleep(5000);
        return new ReturnMessage("hello :"+message.getName());
    }

}
