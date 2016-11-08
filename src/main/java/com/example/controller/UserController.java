package com.example.controller;

import com.example.model.Users;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private UserService userService;

    @RequestMapping("/cache")
    @ResponseBody
    public String seeCache(){
        return userService.seeCache();
    }


    @RequestMapping("/list")
    public String getUserList(Model model){
        List<Users> users = userService.getUserList();
        model.addAttribute("users",users);
        model.addAttribute("single",users.get(0));
        return "/user/main";
    }

    @RequestMapping(value="/one/{id}")
    public String getOne(@PathVariable("id") Integer id,Model model){
        Users users = userService.findOne(id);
        if(users!=null){
            model.addAttribute("user",users);
            return "/user/detail";
        }else {
            return "404";
        }
    }

    @RequestMapping(value="/save")
    public String saveOne(@RequestBody Users user){
        userService.saveOne(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value="/remove/{id}")
    public String removeOne(@PathVariable("id") Integer id){
        Users users = new Users();
        users.setId(id);
        int i = userService.removeOne(users);
        return "redirect:/user/list";
    }

    @RequestMapping(value="/update")
    public String updateOne(@RequestBody Users user){
        int i = userService.updateOne(user);
        return  "redirect:/user/list";
    }
}
