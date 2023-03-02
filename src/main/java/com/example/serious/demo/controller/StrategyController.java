package com.example.serious.demo.controller;

import com.example.serious.demo.StrategyContext;
import com.example.serious.demo.service.StrategyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StrategyController {

    @Autowired
    private StrategyContext strategyContext;

    @PostMapping("/getStrategy")
    public String getStrategy(@RequestParam(name = "strategyType") String strategyType){
        StrategyInterface strategy = strategyContext.getStrategy(strategyType);
        strategy.go();
        return "";
    }
}
