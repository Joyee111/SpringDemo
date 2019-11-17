package com.example.serious.demo.service.Impl;

import com.example.serious.demo.dao.BlogDao;
import com.example.serious.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    BlogDao blogDao;
    @Override
    public void iocTest() {
        blogDao.getBlog();
        System.out.println("iocTest1");
    }
}
