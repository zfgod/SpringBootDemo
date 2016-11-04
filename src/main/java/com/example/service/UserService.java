package com.example.service;

import com.example.mapper.UserMapper;
import com.example.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author: zf
 * Date: 2016/11/4  11:29
 * Description: 用户 业务层
 *   缓存配置
 */
@Service
@Transactional//此类所有方法开启事务支持
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Cacheable(value = "users",key = "#user.id")
    //使用名称为users的cache,存入缓存的key为user的id
    public Users findOne(Integer id) {
        return userMapper.findOne(id);
    }

    @CachePut(value = "users",key = "#user.id")
    //存入或者更新 users缓存中 key为user的id 的缓存
    public int saveOne(Users user){
        return userMapper.insertSelective(user);
    }

    @CachePut(value = "users",key = "#user.id")
    public int updateOne(Users user){
        return userMapper.updateByPrimaryKeySelective(user);
    }


    @Cacheable(value = "users")
    //存入缓存user中，
    //key没有指定，即方法参数作为key ，其他注解也是，key默认为方法参数
    public List<Users> getUserList() {
        return userMapper.selectByExample(null);
    }

//    @CacheEvict(value = "users",allEntries = true)
//    @CacheEvict(value = "users")
//    allEntries = true  移除该缓存名称下面所有key的缓存
//    如果不知道 key,默认key = 方法参数
    @CacheEvict(value = "users",key = "#user.id")
    public int removeOne(Users user){
        return userMapper.deleteByPrimaryKey(user);
    }

}
