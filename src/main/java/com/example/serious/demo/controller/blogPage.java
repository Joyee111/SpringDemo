package com.example.serious.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.serious.demo.dao.BlogDao;
import com.example.serious.demo.domain.Blog;
import com.example.serious.demo.domain.TestEntity;
import com.example.serious.demo.mapper.BlogMapper;
import com.example.serious.demo.util.JedisUtils;
import com.example.serious.demo.util.RandomUtil;
import com.sun.media.jfxmedia.logging.Logger;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolAbstract;
import redis.clients.jedis.params.SetParams;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * blog页面
 */
@Controller
public class blogPage {
    @Autowired
    BlogDao blogDao;

    @RequestMapping("/getBlog")
    @ResponseBody
    public String getBlogInfo(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Jedis jedis = JedisUtils.getInstance("localhost");
        String author = request.getParameter("author") + request.getRequestedSessionId();
        JSONArray array;

        for (int i = 0; i < 1000000; i++) {
            TestEntity testEntity = new TestEntity();
            testEntity.setKey2(i);
            testEntity.setKey1(RandomUtil.getRandomString(10));
            testEntity.setKey3(RandomUtil.getRandomString(10));
            testEntity.setKey_part1(RandomUtil.getRandomString(10));
            testEntity.setKey_part2(RandomUtil.getRandomString(10));
            testEntity.setKey_part3(RandomUtil.getRandomString(10));
            testEntity.setCommon_field(RandomUtil.getRandomString(10));
            blogDao.insertData(testEntity);
        }

        if (jedis.exists("blogs_" + author)) {
            String blogResult = jedis.get("blogs_" + author);
            array = JSONArray.parseArray(blogResult);
            System.out.println(session.getId());
        } else {
            //第一次查询当前用户blog信息
            List<Blog> listBlog = blogDao.findAllBlog();
            array = JSONArray.parseArray(JSON.toJSONString(listBlog));
            jedis.set("blogs_" + author, JSON.toJSONString(listBlog), SetParams.setParams().ex(3600));
            System.out.println("第一次登陆的" + session.getId());
        }


        return array.toJSONString();
    }

    @RequestMapping(value = "/uploadBlog", method = RequestMethod.POST)
    @ResponseBody
    public String uploadBlog(HttpServletRequest request, @RequestParam(value = "file") MultipartFile multipartFile, @RequestParam(value = "title") String title, @RequestParam(value = "content") String content, @RequestParam(value = "usercode") String usercode) {
        Jedis jedis = JedisUtils.getInstance("localhost");
        try {

            //获取根节点
            String path = ResourceUtils.getURL("classpath:").getPath();
            File file = new File(path);
            String absolutePath = file.getAbsolutePath();
            FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(absolutePath + "/static/assets/img/" + multipartFile.getOriginalFilename()));
            Blog blog = new Blog();
            blog.setAuthor("admin");
            blog.setTitle(title);
            blog.setDescription(content);
            blog.setS_atime(new Date());
            blog.setImgUrl("/assets/img/" + multipartFile.getOriginalFilename());
            int i = blogDao.setBlog(blog);
            if (i > 0) {
                long m = jedis.del("blogs_" + usercode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            return "{\n" +
                    "  \"code\": 0\n" +
                    "  ,\"msg\": \"\"\n" +
                    "  ,\"data\": {\n" +
                    "    \"src\": \"" + title + content + "\"\n" +
                    "  }\n" +
                    "}  ";
        }
    }
}
