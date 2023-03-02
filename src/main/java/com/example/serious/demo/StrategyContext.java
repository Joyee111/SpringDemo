package com.example.serious.demo;

import com.example.serious.demo.service.StrategyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StrategyContext {

    @Autowired
    private Map<String,StrategyInterface> strategyInterfaceMap;

    public  StrategyInterface getStrategy(String annotationValue) {
        return strategyInterfaceMap.get(annotationValue);
    }
}
