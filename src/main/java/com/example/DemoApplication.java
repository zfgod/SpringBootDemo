package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //此类作为controller进行注入
@ServletComponentScan //
@MapperScan("com.example.mapper")
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@SpringBootApplication
public class DemoApplication {
	@RequestMapping("/")
	String home(){
		return "hello SpringBoot!";
	}

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
}
