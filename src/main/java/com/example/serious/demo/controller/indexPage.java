package com.example.serious.demo.controller;

import com.example.serious.demo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;


@RestController
public class indexPage {
    @Autowired
    DemoService demoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(indexPage.class);

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, BrokenBarrierException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {

            demoService.iocTest(i,countDownLatch);
        }
        countDownLatch.await();
        System.out.println("全部已完成···");
        String uri = request.getRequestURI().toString();
        System.out.println("统一执行"+uri);

//        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
//            System.out.println("汇总1 ...");
//
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println("汇总2 ...");
//        });
//        //for(int u = 0, u < 2;u ++)//开两次屏障使用
//        for(int i = 0;i < 3;i ++) {
//            new Thread(() -> {
//                try {
//                    Thread.sleep((long)(Math.random() * 2000));
//
//                    int randomInt = new Random().nextInt(500);
//                    System.out.println("hello " + randomInt);
//
//                    cyclicBarrier.await();
//
//                    System.out.println("world " + randomInt);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
        return "index";
    }
}
