package com.example.controller;

import com.example.mapper.UserMapper;
import com.example.model.Usera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * author: zf
 * Date: 2016/11/1  11:19
 * Description: 用户
 * thymeleaf 模板学习
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/list")
    public String getUserList(Model model){
        List<Usera> users = userMapper.selectByExample(null);
        model.addAttribute("users",users);
        model.addAttribute("single",users.get(0));
        return "/user/main";
    }
}
