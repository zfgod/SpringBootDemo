package com.example.mapper;

import com.example.model.Users;
import com.github.abel533.mapper.Mapper;


/**
 * author: zf
 * Date: 2016/11/1  11:20
 * Description:
 */

public interface UserMapper extends Mapper<Users> {


    Users findOne(Integer id);
}
