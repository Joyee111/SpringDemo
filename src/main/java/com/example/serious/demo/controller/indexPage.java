package com.example.serious.demo.controller;

import com.example.serious.demo.entity.FileEntity;
import com.example.serious.demo.mq.core.stream.MailProducer;
import com.example.serious.demo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class indexPage {
    @Autowired
    DemoService demoService;

    @Autowired
    private MailProducer mailProducer;

    private static final Logger LOGGER = LoggerFactory.getLogger(indexPage.class);

    @RequestMapping("/index")
    public String index(@RequestBody @Valid FileEntity fileEntity) {

        mailProducer.sendMailSendMessage(fileEntity);
        return "index";
    }
}
