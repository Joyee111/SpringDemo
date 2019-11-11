package com.example.serious.demo.controller;

import com.example.serious.demo.service.demoService;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class indexPage {
    @Autowired
    com.example.serious.demo.service.demoService demoService;
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response){
        demoService.iocTest();
        String uri = request.getRequestURI().toString();
        System.out.println(uri);
        return "index";
    }
}
