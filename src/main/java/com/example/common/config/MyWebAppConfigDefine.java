package com.example.common.config;


import com.example.common.interceptors.LoginInterceptor;
import com.example.common.interceptors.OtherInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * author: zf
 * Date: 2016/10/26  13:37
 * Description:web应用配置 继承适配器WebMvcConfigurerAdapter,自定义配置
 */
@Configuration
public class MyWebAppConfigDefine extends WebMvcConfigurerAdapter {
    /**
     * 重写拦截器定义
     * 添加自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //按添加的先后顺序依次执行
        registry.addInterceptor(new OtherInterceptor()).addPathPatterns("/*");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/*");
        super.addInterceptors(registry);
    }

    /***
     * springBoot默认静态资源处理：
     *    /** 映射    /static  访问static目录下文件不需要带上/static/文件,直接 contentPath/文件
     *    /webjars/** 映射  classpath:/META-INF/resources/webjars/
     * 增设资源访问路径,hander匹配不覆盖上面的路径则不与上面冲突,都可以使用:只可添加一个.
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//  http://localhost:8080/myRes/fengjing2.jpg 》   src\main\resources\myRes\fengjing2.jpg
        registry.addResourceHandler("/myRes/**").addResourceLocations("classpath:/myRes/");
        super.addResourceHandlers(registry);
    }
}
