package com.example.controller;

import com.example.model.Student;
import com.example.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author: zf
 * Date: 2016/10/27  9:22
 * Description:
 */
@RestController
@RequestMapping("/stu")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Value("${testParam}")
    private  String testParam;//application.properties
    @Value("${testParams}")
    private  Object testParam2;//application-{profile}.properties

    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/list")
    public Object getList(){
        logger.info("查询学生列表数据。。。");
        List<Student> list = studentService.getList();
        if(list!=null && list.size()>0){
            logger.info("查询学生列表成功！");
        }
        return list;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object getOne(@PathVariable("id") Integer id){
        logger.info("查询学生信息ID="+id);
        Student student = studentService.findOne(id);
        if(student!=null){
            logger.info("查询学生信息成功！");
        }
        return student;
    }

    @RequestMapping(value = "/query")
    public Object queryStu(){
        System.out.println(testParam);
        System.out.println(testParam2);
        logger.info("查询学生..");
        List<Student> list = studentService.queryStu();
        if(list!=null && list.size()>0){
            logger.info("查询学生信息成功！");
        }
        return list;
    }
    @RequestMapping(value = "/update/{id}")
    public Object updateStu(@PathVariable("id")Integer id){
        Student student = new Student() ;
        student.setId(id);
        student.setAge(45);
        return studentService.updateStu(student);
    }
}
