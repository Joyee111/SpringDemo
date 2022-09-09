package com.example.serious.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacos")
@RefreshScope
public class NacosController {

    @Value("${config.info}")
    private String info;     //该属性值是从nacos配置中心拉取到的配置

    @GetMapping("/testConfig")
    public String testConfig() {
        return info;
    }
}
