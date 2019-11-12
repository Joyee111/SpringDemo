package com.example.serious.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class indexPage {
    @Autowired
    com.example.serious.demo.service.demoService demoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(indexPage.class);

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response){

        demoService.iocTest();
        String uri = request.getRequestURI().toString();
        System.out.println(uri);
        return "index";
    }
}
