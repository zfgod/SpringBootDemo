package com.example.common.cacheCustomer;

import net.sf.ehcache.CacheManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
       public void after(JoinPoint point){
           MethodSignature signature = (MethodSignature) point.getSignature();
           Method method = signature.getMethod();
           MyCacheEvict cacheEvict = method.getAnnotation(MyCacheEvict.class);//获取注解
           String targetName  = point.getTarget().getClass().getName();
//           String simpleName  = point.getTarget().getClass().getSimpleName();
           String methodName  = point.getSignature().getName();
           Object[] arguments = point.getArgs();//获取方法入参
           String[] paramNames = ReflectParamNames.getNames(targetName, methodName);//反射得到形参名称
           //反射得到注解上的属性,这里只处理 keys和 regex 两种策略
           String value = cacheEvict.value();//缓存名称
// 多个key的清除
           String[] keys = cacheEvict.keys();
           List<String> list = new ArrayList<>();
           if(keys!=null && keys.length>0){
               for (String key : keys) {
                   list.add(getSpelString(key,arguments,paramNames)) ;
               }
               //调用缓存策略工具,实现缓存清除
               CacheUtils.removeKeys(list,value,cacheManager);
           }

//匹配式key的清除
           String regex = cacheEvict.keyRegex();
           if(StringUtils.isNotBlank(regex)){
               CacheUtils.removeKeysWithRegex(regex, value,cacheManager);
           }
       }
//       @Before("annotationPointCut()")
//       public void before(JoinPoint point){
//           MethodSignature signature = (MethodSignature) point.getSignature();
//           Method method = signature.getMethod();
//           MyCacheEvict cacheEvict = method.getAnnotation(MyCacheEvict.class);
//           String targetName  = point.getTarget().getClass().getName();
////           String simpleName  = point.getTarget().getClass().getSimpleName();
//           String methodName  = point.getSignature().getName();
//           Object[] arguments = point.getArgs();
//           String[] paramNames = ReflectParamNames.getNames(targetName, methodName);
//           String keys = cacheEvict.key();
//           String key = getSpelString(keys,arguments,paramNames);
//           System.out.println(key);
//       }
//  通过springEL获取真实值
    private String getSpelString(
            String key, Object[] arguments,String[] paramNames) {
//        String key;
//        if (keys.length() > 0) {
//            key = "'"+simpleName+"."+methodName + ".'+" +  keys;
//        }else{//没传key
//            key = simpleName+"."+methodName;
//        }
        String[] thisParamNames = ParameterMap.get(key);
        if (thisParamNames == null){
            thisParamNames = paramNames;
            ParameterMap.put(key, thisParamNames);
        }
        if(key.length() > 0){
            //spring EL 表达式
            key = SpelParser.getKey(key, thisParamNames, arguments);
        }
        return key;
    }

}
