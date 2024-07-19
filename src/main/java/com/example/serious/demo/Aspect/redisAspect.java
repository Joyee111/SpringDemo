package com.example.serious.demo.Aspect;

import cn.hutool.core.bean.BeanUtil;
import com.example.serious.demo.entity.FileEntity;
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
        FileEntity fileEntity = BeanUtil.copyProperties(proceedingJoinPoint.getArgs()[length - 1], FileEntity.class);
        Boolean delete = redisTemplate.delete(fileEntity.getId());
        proceedingJoinPoint.proceed();
        //after
        log.info("是否删除："+delete);
    }
}
