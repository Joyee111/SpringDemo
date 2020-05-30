package com.example.serious.demo.controller;

import com.example.serious.demo.mapper.UserMapper;
import com.example.serious.demo.service.Impl.ansyTestService;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
    @Test
    public void test1 () throws IOException {
        int a = 13&17;
        System.out.println("结果："+a);
        Map map = new ConcurrentHashMap();
        map.put("1231","321");
        SqlSessionFactory sqlSessionFactory=null;
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        Reader reader = Resources.getResourceAsReader("application.properties");
        sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.getMapper(UserMapper.class);

    }

}
