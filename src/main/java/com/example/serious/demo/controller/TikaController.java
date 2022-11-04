package com.example.serious.demo.controller;

import com.example.serious.demo.service.TikaService;
import com.example.serious.demo.util.FileUtil;
import org.elasticsearch.cluster.routing.allocation.decider.Decision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/tika")
public class TikaController {

    @Autowired
    private TikaService tikaService;

    @RequestMapping("/send")
    public String sendTika(MultipartFile file) throws IOException, InterruptedException {
        tikaService.tikaByHttp(FileUtil.multipartFileToFile(file));
        return "DONE";
    }
}
