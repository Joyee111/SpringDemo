package com.example.serious.demo.controller;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PDFtoPictureController  {
    @RequestMapping(value = "/downloadPic.do")
    @ResponseBody
    public String getPictureByte(@RequestParam("image") String bytePic) throws IOException {
        String[] byteNew = bytePic.split("base64,");
        String filePath = "D:\\workspace\\SpringDemo\\src\\main\\resources\\static";
        File dir = new File(filePath);
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.getDecoder().decode(byteNew[1]);
            File file = new File(filePath + "\\" + "123.png");
            fos = new java.io.FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "OK";
    }
    @RequestMapping(value="/searchBilibili.do")
    @ResponseBody
    public Map<String,Object> searchBilibili(@RequestParam("keyword") String keyword) throws IOException {
        HttpGet httpGet = new HttpGet("https://api.bilibili.com/x/web-interface/search/type?keyword="+keyword+"&search_type=video");

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);

        HttpEntity httpEntity = closeableHttpResponse.getEntity();
        String s = EntityUtils.toString(httpEntity);
        Map<String, Object> res = null;
        try {
            Gson gson = new Gson();
            res = gson.fromJson(s, new TypeToken<Map<String, Object>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
        }

        System.out.println(res.toString());
        return res;
    }



    public String bytetoString(@RequestParam("byte") String bytes){
        byte[] bytePics = Base64.getDecoder().decode(bytes);
        for(byte bytePic : bytePics){
            bytePic+=256;
        }
        return "";
    }
}
