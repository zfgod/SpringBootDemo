//
//package com.example.study.jms;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
//
///**
// * author: zf
// * Date: 2016/12/5  19:06
// * Description:
// */
//
//@Configuration
//public class MsgApplication  {
//    @Value("${jms.broker.url}")
//    String url;
//    @Bean
//    public ActiveMQConnectionFactory activeMQConnectionFactory() {
//        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
////        factory.setTrustedPackages(Arrays.asList("com.my.package,sys.model.AmqObject".split(",")));
//        factory.setTrustAllPackages(true);
//        return factory;
//
//
//    }
//}
//
