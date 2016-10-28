package com.example.common.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * author: zf
 * Date: 2016/10/26  10:41
 * Description: servletContext 监听 web应用启动即创建，web应用停止则销魂
 */
@WebListener
public class CusServletContentListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("servlet创建："+servletContextEvent.getServletContext().toString());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("servlet销毁："+servletContextEvent.getServletContext().toString());
    }
}
