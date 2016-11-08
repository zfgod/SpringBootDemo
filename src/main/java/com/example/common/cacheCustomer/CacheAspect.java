package com.example.common.cacheCustomer;

import com.example.study.aop.LogAction;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * author: zf
 * Date: 2016/11/8  11:08
 */
@Aspect//声明一个切面
@Component//让此切面成为受Spring容器管理的Bean
public class CacheAspect {

       @Pointcut("@annotation(MyCacheEvict)")
       //声明切点
       public void annotationPointCut(){
       }


       @Autowired
       CacheManager cacheManager;

       @After("annotationPointCut()")
       public void after(JoinPoint joinpoint){
           MethodSignature signature = (MethodSignature) joinpoint.getSignature();
           Method method = signature.getMethod();
           Parameter[] parameters = method.getParameters();
           MyCacheEvict myCacheEvict = method.getAnnotation(MyCacheEvict.class);
           //反射得到注解上的属性
           String value = myCacheEvict.value();
           String key = myCacheEvict.key();
           SpelExpressionParser spelParser = new SpelExpressionParser();
           Expression expression = spelParser.parseExpression(key);
           String regex = myCacheEvict.keyRegex();
           String[] keys = myCacheEvict.keys();
           boolean allEntries = myCacheEvict.allEntries();
           System.out.println("注解式缓存策略---"+value+"-"+key);//
           Cache cache = cacheManager.getCache(value);
           Element element = cache.get(key);
           System.out.println(element);
       }

       @Before("annotationPointCut()")
       public void before(JoinPoint point){
           MethodSignature signature = (MethodSignature) point.getSignature();
           Method method = signature.getMethod();
           Parameter[] parameters = method.getParameters();
           MyCacheEvict cacheEvict = method.getAnnotation(MyCacheEvict.class);
           long startTime = System.currentTimeMillis();
           String targetName = point.getTarget().getClass().getName();
           String simpleName = point.getTarget().getClass().getSimpleName();
           String methodName = point.getSignature().getName();
           Object[] arguments = point.getArgs();
           //重新加载 要更新的缓存方法名
           if(cacheEvict.reLoad()){
               methodName = cacheEvict.method();
           }

           String key = null;
//        没传key
           if (cacheEvict.key().length() > 0) {
               key = "'"+simpleName+"."+methodName + ".'+" +  cacheEvict.key();
           }else{
               key = simpleName+"."+methodName;
           }

           String[] paramNames = ParameterMap.get(key);
           if (paramNames==null){
//          反射得到形参名称
               paramNames = ReflectParamNames.getNames(targetName, methodName);
               ParameterMap.put(key, paramNames);
           }

           if(cacheEvict.key().length() > 0){
//          spring EL 表达式
               key = SpelParser.getKey(key, cacheEvict.condition(), paramNames, arguments);
           }

           if(key.length()>200){
//          logger.warn("+++cache key length over max 200!");
           }
       }

}
