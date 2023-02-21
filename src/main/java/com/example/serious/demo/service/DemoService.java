package com.example.serious.demo.service;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;

public interface DemoService {
    public Boolean iocTest(Integer i, CountDownLatch countDownLatch) throws InterruptedException, BrokenBarrierException;
}
