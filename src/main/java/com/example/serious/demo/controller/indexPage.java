package com.example.serious.demo.controller;

import com.example.serious.demo.domain.User;
import com.example.serious.demo.mapper.UserMapper;
import com.example.serious.demo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class indexPage {
    @Autowired
    DemoService demoService;


    private static final Logger LOGGER = LoggerFactory.getLogger(indexPage.class);

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response){

        demoService.iocTest();
        String uri = request.getRequestURI().toString();
        System.out.println(uri);
        return "index";
    }
}
