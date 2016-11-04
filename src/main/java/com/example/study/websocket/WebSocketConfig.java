/*
package com.example.study.websocket;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;


*/
/**
 * author: zf
 * Date: 2016/11/1  17:21
 * Description: webSocket 配置
 *//*

@Configuration
//这时控制器支持使用@MessageMapping
@EnableWebSocketMessageBroker//开启使用STOMP协议来传输基于代理（message broker）的消息
public class WebSocketConfig
        extends AbstractWebSocketMessageBrokerConfigurer {
    */
/**
     * 注册协议的节点endpoint，并映射指定url
     * @param registry
     *//*

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册一个stomp协议的endpoint,并指定使用sockJS协议
        registry.addEndpoint("/endPointZfGod").withSockJS();
         //注册一个名为/endPointChat 的endpoint
        registry.addEndpoint("/endPointChat").withSockJS();
    }

    */
/**
     * 配置消息代理（message broker)
     * @param registry
     *//*

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //广播式应配置一个/topic 消息代理
        //点对点式 应配置一个/queue的消息代理
        registry.enableSimpleBroker("queue","/topic");
    }

}
*/
