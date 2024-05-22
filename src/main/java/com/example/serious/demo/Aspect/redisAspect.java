package com.example.serious.demo.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class redisAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation( com.example.serious.demo.util.RedisDelayDeleteAspect)")
    public void asp(){
        System.out.println("asp方法");
    }

    @Around("asp()")
    public  void doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //before
        int length = proceedingJoinPoint.getArgs().length;
        Object arg = proceedingJoinPoint.getArgs()[length-1];
        redisTemplate.delete(arg);
        proceedingJoinPoint.proceed();
        //after
        redisTemplate.delete(arg);
    }
}
