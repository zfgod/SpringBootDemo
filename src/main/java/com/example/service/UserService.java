package com.example.service;

import com.example.mapper.UserMapper;
import com.example.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

    @Cacheable(value = "users",key = "'user_'.concat(#id)")
//   @Cacheable(value = "users",key = "'user_'+#id")
    //使用名称为users的cache,存入缓存的key为user_id(springEL获取入参)
    //SpringEl 拼接： 使用concat函数或者使用 '+' 号拼接，多使用效率高的concat
    //
    public Users findOne(Integer id) {
        return userMapper.findOne(id);
    }

    @CachePut(value = "users",key = "'user_'.concat(#u.getId())")
//    @CacheEvict(value = "users",key = "")
    //存入或者更新 users缓存中 key为user_id 的缓存
    //当存入成功，直接把此用户缓存到users中 key= user拼接上id
    public Users saveOne(Users u){
//    方法的返回值 作为缓存的value存储,所以需要返回user对象并带有id值
        u.setId(null);
        userMapper.insertSelective(u);
        return u;
    }


    @CacheEvict(value = "users",key = "'user_'.concat(#user.getId())")
//    更新的时候由于 更新的对象内容比 查询需要的字段会少很多,所以选择清除缓存,调用查询接口重新加入缓存
    public int updateOne(Users user){
        return userMapper.updateByPrimaryKeySelective(user);
    }


    @Cacheable(value = "users",key = "'list'")
    //存入缓存user中
    //key没有指定，即默认方法参数作为key 其他注解也是，key默认为方法入参
    public List<Users> getUserList() {
        return userMapper.selectByExample(null);
    }

//    @CacheEvict(value = "users",allEntries = true)
//    @CacheEvict(value = "users")
//    allEntries = true  移除该缓存名称下面所有key的缓存
//    如果不知道 key,默认key = 方法参数
    @CacheEvict(value = "users",key = "'user_'.concat(#user.id)")
    public int removeOne(Users user){
        return userMapper.deleteByPrimaryKey(user);
    }

}
