package com.example.serious.demo.service.Impl;

import com.example.serious.demo.service.AbstractStrategy;
import com.example.serious.demo.service.StrategyInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("EAT")
@Slf4j
public class EatStrategyInterfaceImpl extends AbstractStrategy implements StrategyInterface {

    @Override
    public void doRun() {
        log.info("doRun实现");
    }

    @Override
    public void after() {
        log.info("after实现");
    }

    @Override
    public void init() {
        log.info("init实现");
    }

    @Override
    public void go() {
        run();
    }
}
