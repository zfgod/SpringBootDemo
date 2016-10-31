package com.example.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * author: zf
 * Date: 2016/10/31  18:39
 * Description: 读取自定义文件 customer.properties，类型安全配置参数
 */
@Component
@ConfigurationProperties(prefix = "customer",locations = {"classpath:config/customer.properties"})
public class CustomerParam {
    private Integer age;
    private String  name;

    @Override
    public String toString() {
        return "CustomerParam{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
