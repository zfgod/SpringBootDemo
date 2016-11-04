/*
package com.example.study.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

*/
/**
 * author: zf
 * Date: 2016/11/2  13:41
 * Description: 点对点 消息发送  这里用security做登录和拦截配置
 * 不测试点对点 注释掉 @Configuration  @EnableWebSecurity
 *//*

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    */
/*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/wsLogin").permitAll()//放行路径
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/wsLogin")//登录页面请求路径
                .defaultSuccessUrl("/wsChat")//登录成功跳转路径
                .permitAll()
                .and()
                .logout().permitAll();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("abc").password("abc").roles("USER")
                .and()
                .withUser("zf").password("zf").roles("USER");
        //内存分配两个用户及对应密码
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //static下面资源放行
            web.ignoring().antMatchers("/resources/static*//*
*/
/**");
    }*//*

}
*/
