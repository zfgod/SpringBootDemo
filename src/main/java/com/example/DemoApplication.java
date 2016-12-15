package com.example;

import com.example.model.CustomerParam;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //此类作为controller进行注入
@EnableAsync    //支持异步请求
@EnableCaching  //开启缓存支持
@ServletComponentScan //
@MapperScan("com.example.mapper")//mybatis mapper接口扫描
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@SpringBootApplication //项目核心注解，开启自动配置.
public class DemoApplication {
//  Spring EL 注入properties文件参数
	@Value("${project.name}")
	private String projectName;

	@Autowired
	private CustomerParam customerParam;

    //项目启动入口
	@RequestMapping("/")
	String home(){

		return "hello SpringBoot...-"+projectName+" i am "+customerParam.getName();
	}

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

}
