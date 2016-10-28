package com.example.study.aware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import sun.misc.IOUtils;

import java.io.IOException;

/**
 * author: zf
 * Date: 2016/10/28  16:27
 * Description:
 */
@Service
public class AwareDemoService implements BeanNameAware,ResourceLoaderAware{
    private String beanName;
    private ResourceLoader loader;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.loader = resourceLoader;
    }

    public void outputResult(){
        System.out.println("Bean的名称为："+beanName);
        Resource resource =
                loader.getResource("classpath:/myRes/aa.xml");
        try {
            System.out.println("re:"+ resource.getInputStream().toString());
        }catch (IOException e){
           e.printStackTrace();
        }
    }
}
