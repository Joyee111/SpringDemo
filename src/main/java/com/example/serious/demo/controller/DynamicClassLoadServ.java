package com.example.serious.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Controller
public class DynamicClassLoadServ extends ClassLoader{
@GetMapping(value = "/setClass")
    protected String findClass(@RequestParam(value = "uri") String packageUri , @RequestParam(value = "name") String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        byte[] classData = getClassData("D:\\workspace\\SpringDemo\\target\\classes",name);
        if (classData == null) {
            throw new ClassNotFoundException();
        }
        else {
            Class myClass = null;
            if(super.findLoadedClass(name) == null){
                myClass = defineClass(name, classData, 0, classData.length);
            }else{
                System.out.println("===============动态类已经加载");
                myClass = Class.forName(name);
            }


            for(Method m : myClass.getMethods()) {
                System.out.println(m.getName());
                if(m.getName().equals("index")){
                    String result = (String)m.invoke(myClass.newInstance());
                    return result;
                }else{
                    return "error";
                }
            }
            return "final";

        }
    }

    private byte[] getClassData(String packageUri , String className) throws IOException {
        String path = classNameToPath(packageUri,className);
        ByteArrayOutputStream baos = null;
        try {
            try (InputStream ins = new FileInputStream(path)) {
                baos = new ByteArrayOutputStream();
                int bufferSize = 4096;
                byte[] buffer = new byte[bufferSize];
                int bytesNumRead = 0;
                while ((bytesNumRead = ins.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesNumRead);
                }
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            baos.close();
        }
        return null;
    }

    private String classNameToPath(String packageUri , String className) {
        return packageUri.replace('.', File.separatorChar) + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
    }
}