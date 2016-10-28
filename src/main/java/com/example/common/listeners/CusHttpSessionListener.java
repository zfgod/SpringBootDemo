package com.example.common.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * author: zf
 * Date: 2016/10/26  10:40
 * Description: httpSession监听
 * 浏览器与web应用初次建立访问（动态资源）创建，session自动过期后创建，会话被主动销毁后创建
 */
@WebListener()
public class CusHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("session创建："+httpSessionEvent.getSession().toString());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("session销毁："+httpSessionEvent.getSession().toString());
    }

}
