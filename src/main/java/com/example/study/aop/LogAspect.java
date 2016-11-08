package com.example.study.aop;

import javassist.bytecode.SignatureAttribute;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * author: zf
 * Date: 2016/10/28  11:11
 * Description: 使用aop完成日志书写
 */
@Aspect//声明一个切面
@Component//让此切面成为受Spring容器管理的Bean
public class LogAspect {
       @Pointcut("@annotation(com.example.study.aop.LogAction)")
       //声明切点
       public void annotationPointCut(){};

       @After("annotationPointCut()")
       public void after(JoinPoint joinpoint){
           MethodSignature signature = (MethodSignature) joinpoint.getSignature();
           Method method = signature.getMethod();
           LogAction logAction = method.getAnnotation(LogAction.class);
           //反射得到注解上的属性，然后做日志记录
           System.out.println("注解式拦截---"+logAction.name()+"-"+logAction.key());//
       }


       @Before("execution(* com.example.study.aop.DemoMethodService.*(..))")
//       @Before("execution(* com.example.study..*.*(..))")
       public void before(JoinPoint joinpoint){
         MethodSignature signature = (MethodSignature) joinpoint.getSignature();
         Method method = signature.getMethod();
         //反射得到注解上的属性，然后做日志记录
         System.out.println("方法规则式拦截--"+method.getName());//
       }

}
