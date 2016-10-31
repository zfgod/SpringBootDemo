package com.example.common.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * author: zf
 * Date: 2016/10/26  10:29
 * Description: 随web应用生而生，毁而毁
 * asyncSupported = true 支持异步请求
 */
@WebFilter(filterName="myFilter",urlPatterns="/*",asyncSupported = true)
public class BaseFilter implements Filter {

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
//        System.out.println("执行过滤操作");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("过滤器初始化");
    }

}