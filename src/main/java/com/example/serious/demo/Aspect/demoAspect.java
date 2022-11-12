package com.example.serious.demo.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
@Slf4j
@Component
@Aspect
public class demoAspect {
    @Pointcut("@annotation( com.example.serious.demo.util.AspectUtils)")
    public void asp(){

    }
    @Before("asp()")
    public void doBefore(JoinPoint joinPoint) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("类名："+className);
        log.info("方法名"+methodName);
        Arrays.toString(joinPoint.getArgs());
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        log.info(request.getParameter("author") + request.getRequestedSessionId());
        //请求的参数

        Class claz = Class.forName(className);
        for(Method method :claz.getMethods()){
            if(("before"+methodName).equals(method.getName())){
                method.invoke(claz.newInstance() , joinPoint.getArgs());
            }
        }

    }

    @After("asp()")
    public void doAfter(JoinPoint joinPoint) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("类名："+className);
        log.info("方法名"+methodName);
        Arrays.toString(joinPoint.getArgs());
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        log.info(request.getParameter("author") + request.getRequestedSessionId());
        //请求的参数

        Class claz = Class.forName(className);
        for(Method method :claz.getMethods()){
            if(("after"+methodName).equals(method.getName())){
                method.invoke(claz.newInstance() , joinPoint.getArgs());
            }
        }

    }
}
