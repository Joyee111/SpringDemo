package com.example.serious.demo.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacos")
public class NacosController {

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    String nacosConfig;

    @RequestMapping("get")
    public String getConfig() {
        return nacosConfig;
    }
}
