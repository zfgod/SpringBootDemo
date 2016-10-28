package com.example.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * author: zf
 * Date: 2016/10/26  11:54
 * Description: session
 */
@RestController
public class SessionAnalyzeController {

    @RequestMapping(value = "/a")
    public String analyzeSession(HttpServletRequest request,HttpSession session){
        ServletContext servletContext = request.getServletContext();
        String id = session.getId();
        return id;
    }
}
