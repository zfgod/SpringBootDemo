package com.example.service;

import com.example.DemoApplication;
import com.example.model.Users;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


/**
 * author: zf
 * Date: 2016/11/4  11:47
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringApplicationConfiguration(classes = DemoApplication.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration // 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
//@Transactional //确保每次测试后的数据会回滚 如果测试数据也想添加到数据库，可不用，我这里测试缓存生效情况，选择存入数据库
public class UserServiceTest {

    //注入你要测试的业务层bean
    @Autowired
    private UserService userService;

    @Autowired
    CacheManager cacheManager;

    @Before
    public void setUp(){

    }

    @Test
    public void testFindOne() throws Exception {
        Users one = userService.findOne(1);
        System.out.println(one);
        Cache users = cacheManager.getCache("users");
        System.out.println(users);
    }

    @Test
    public void testGetUserList() throws Exception {

    }
}