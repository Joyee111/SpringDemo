package com.example.serious.demo.controller;

import com.example.serious.demo.service.Impl.ansyTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.Future;

@Controller
public class ansyTestController {

    @Autowired
    ansyTestService ansyTestService;

    @RequestMapping("test")
    public  Integer methodA() throws Exception{
        long start = System.currentTimeMillis();
        Future<Integer> future1 =ansyTestService.methodB();
        long end = System.currentTimeMillis();
        return 1;
    }

}
