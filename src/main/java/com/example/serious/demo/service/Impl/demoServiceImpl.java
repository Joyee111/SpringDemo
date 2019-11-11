package com.example.serious.demo.service.Impl;

import com.example.serious.demo.service.demoService;
import org.springframework.stereotype.Service;

@Service
public class demoServiceImpl implements demoService {
    @Override
    public void iocTest() {
        System.out.println("iocTest1");
    }
}
