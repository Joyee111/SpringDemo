package com.example.serious.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Controller
public class PDFtoPictureController {
    @RequestMapping(value = "/downloadPic.do")
    @ResponseBody
    public String getPictureByte(@RequestParam("image") String bytePic) throws IOException {
        String[] byteNew = bytePic.split("base64,");
        String filePath = "D:\\workspace\\demo\\src\\main\\resources\\static";
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

    public String bytetoString(@RequestParam("byte") String bytes){
        byte[] bytePics = Base64.getDecoder().decode(bytes);
        for(byte bytePic : bytePics){
            bytePic+=256;
        }
        return "";
    }
}
