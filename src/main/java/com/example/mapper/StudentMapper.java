package com.example.mapper;


import com.example.model.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * author: zf
 * Date: 2016/10/27  11:10
 * Description:
 */
//@Mapper
//
public interface StudentMapper extends  com.github.abel533.mapper.Mapper<Student> {

    Student findOne(Integer id);

    List<Student> queryStu();
}
