package com.example.serious.demo.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ExecutorConfig {
    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(16);
        executor.setKeepAliveSeconds(200);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //等待所有任务都完成再继续销毁其他得Bean
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //线程池中任务得等待时间，如果超过这个时候还没销毁就强制销毁以确保应用最后能关闭，而不是阻塞
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }
}
