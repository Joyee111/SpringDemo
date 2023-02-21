package com.example.serious.demo.service.Impl;

import com.example.serious.demo.dao.BlogDao;
import com.example.serious.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    BlogDao blogDao;

    @Async("threadPoolTaskExecutor")
    @Override
    public Boolean iocTest(Integer i, CountDownLatch countDownLatch) throws InterruptedException, BrokenBarrierException {
            System.out.println(Thread.currentThread().getName() +":  线程开始干活儿。 当前线程"+Thread.currentThread().getName());
            Thread.sleep(5000);

            System.out.println(Thread.currentThread().getName() +":  当前线程完成任务"+Thread.currentThread().getName());
            countDownLatch.countDown();
            return true;
//        blogDao.getBlog();

    }
}
