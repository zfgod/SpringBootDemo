
# SpringBootDemo
  springBoot 微服务框架学习实例,参考书籍《JavaEE的颠覆者Spring Boot》汪云飞

## 后台系统框架：SpringMVC+SpringBoot+Mybatis
   + SpringBoot集成mvc,mybatis,
   + java配置+properties文件注入配置参数,静态文件处理
   + 类型安全的配置（基于properties)
   + 日志locback
   + profile配置
   + springCache,EhCache
   + 开发热部署及开发测试

## 前端：
   + a. 使用thymeleaf模板（th 标签操作数据） springBoot推荐
   + b. 使用静态页面+json数据交互模式（js框架可以任选框架） 适合前后端分离的开发模式

## 其他：
   + 异步请求 controller形式
   + Condition 注解 条件注入bean
   + sse:server send event, 客户端Window.EventSource 支持
   + AsyncConfigurer 异步任务
   + webSocket 广播式通信
   + aop：注解式和方法规则式
      ++ 注解式：自定义注解（名称和属性），aspect切面
      ++ 方法规则式：aspect切面,配置匹配规则execution
   + cache:自定义缓存注解，SpEl解析属性，aspect切面引入cache策略
   + activeMQ
       jmsMessagingTemplate 发送queue、topic消息
       @JmsListener 异步接收 queue/topic 消息（监听队列模式2选1）
