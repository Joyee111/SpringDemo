package com.example.serious.demo.controller;


import org.apache.commons.io.FileUtils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

import java.io.IOException;

/**
 * blog页面
 */
@Controller
public class blogPage {
    @RequestMapping("/getBlog")
    @ResponseBody
    public String getBlogInfo (HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getParameter("data"));
        String data = request.getParameter("data");
        String index = data.split("pdf-")[1];

        return "/assets/img/blog-"+index+".jpg";
    }
    @RequestMapping(value = "/uploadBlog", method = RequestMethod.POST)
    public void uploadBlog(HttpServletRequest request,@RequestParam(value = "img") MultipartFile multipartFile ){
        try {
        Resource resource = new ClassPathResource("");
        String contextPath = resource.getURL().toString();
        File FILE_FOR_WRITE = new File(contextPath+"/assets/img/"+multipartFile.getOriginalFilename());
        FileUtils.writeByteArrayToFile(FILE_FOR_WRITE,multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
