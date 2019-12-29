package com.example.serious.demo.controller;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.serious.demo.dao.BlogDao;
import com.example.serious.demo.domain.Blog;
import com.example.serious.demo.mapper.BlogMapper;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * blog页面
 */
@Controller
public class blogPage {
    @Autowired
    BlogDao blogDao;
    @RequestMapping("/getBlog")
    @ResponseBody
    public String getBlogInfo (HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getParameter("author"));
        String data = request.getParameter("author");
        List<Blog> listBlog = blogDao.findAllBlog();
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(listBlog));

        return array.toJSONString();
    }
    @RequestMapping(value = "/uploadBlog", method = RequestMethod.POST)
    @ResponseBody
    public String uploadBlog(HttpServletRequest request,@RequestParam(value = "file") MultipartFile multipartFile ,@RequestParam(value = "title") String title,@RequestParam(value = "content")String content){
        try {
            //获取根节点
            String path = ResourceUtils.getURL("classpath:").getPath();
            File file = new File(path);
            String absolutePath = file.getAbsolutePath();
            FileCopyUtils.copy(multipartFile.getBytes(),new FileOutputStream(absolutePath+"/static/assets/img/"+multipartFile.getOriginalFilename()));
            Blog blog = new Blog();
            blog.setAuthor("admin");
            blog.setTitle(title);
            blog.setDescription(content);
            blog.setS_atime(new Date());
            blog.setImgUrl("/assets/img/"+multipartFile.getOriginalFilename());
            blogDao.setBlog(blog);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            return "{\n" +
                    "  \"code\": 0\n" +
                    "  ,\"msg\": \"\"\n" +
                    "  ,\"data\": {\n" +
                    "    \"src\": \""+title+content+"\"\n" +
                    "  }\n" +
                    "}  ";
        }
    }
}
