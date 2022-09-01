package com.example.serious.demo.controller;

import com.example.serious.demo.service.TikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/tika")
public class TikaController {

    @Autowired
    private TikaService tikaService;

    @RequestMapping("/send")
    public String sendTika() throws IOException, InterruptedException {
        tikaService.tikaByHttp();
        return "DONE";
    }
}