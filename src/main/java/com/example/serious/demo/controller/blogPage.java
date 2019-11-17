package com.example.serious.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
