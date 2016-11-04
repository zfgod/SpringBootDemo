package com.example.study.websocket;

import java.io.Serializable;

/**
 * author: zf
 * Date: 2016/11/2  13:21
 * Description:
 */
public class Messages implements Serializable{

    private static final long serialVersionUID = 783482513102766386L;
    private String fromUser;
    private String toUser;
    private String msg;

    public Messages() {
    }

    public Messages(String fromUser, String toUser, String msg) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.msg = msg;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
