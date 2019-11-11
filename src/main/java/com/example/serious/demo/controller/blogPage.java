package com.example.serious.demo.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public Object getBlogInfo (String userCode){
        Map<String,String> map  = new HashMap<String,String>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");
        List<Map> list = new ArrayList<Map>();
        list.add(map);
        return JSONArray.toJSONString(map);
    }
}
