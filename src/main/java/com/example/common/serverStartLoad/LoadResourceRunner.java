package com.example.common.serverStartLoad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * author: zf
 * Date: 2016/10/26  15:40
 * Description: 服务器启动时执行加载资源
 */
@Component
@Order(value=1)//加载顺序：1 ,多个可以另起实现类,order=2.。
public class LoadResourceRunner implements CommandLineRunner{
    private static final Logger logger = LoggerFactory.getLogger(LoadResourceRunner.class);
    @Override
    public void run(String... strings) throws Exception {
       logger.info("服务器启动,加载数据中。。。");
    }
}
