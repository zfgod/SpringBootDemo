package com.example.study.websocket;

/**
 * author: zf
 * Date: 2016/11/1  17:36
 * Description: 消息回送
 */
public class ReturnMessage {
    private String resMsg;

    public ReturnMessage(String resMsg) {
        this.resMsg =resMsg;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }
}
