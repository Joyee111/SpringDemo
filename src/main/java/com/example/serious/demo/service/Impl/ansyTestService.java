package com.example.serious.demo.service.Impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
@Service
public class ansyTestService {
    @Async("asyncExecutor")
    public Future<Integer> methodB(){
        try{
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new AsyncResult<>(1);
    }
}
