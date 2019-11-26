package com.example.serious.demo.controller;



import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
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
    @ResponseBody
    public String uploadBlog(HttpServletRequest request,@RequestParam(value = "file") MultipartFile multipartFile ){
        try {
            //获取根节点
            String path = ResourceUtils.getURL("classpath:").getPath();
            File file = new File(path);
            String absolutePath = file.getAbsolutePath();
            FileCopyUtils.copy(multipartFile.getBytes(),new FileOutputStream(absolutePath+"/assets/img"+multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return "上传完成";
        }
    }
}
