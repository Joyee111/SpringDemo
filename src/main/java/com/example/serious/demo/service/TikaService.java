package com.example.serious.demo.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.util.concurrent.Future;

public interface TikaService {

    static final String ABSOLUATE_FILE_PATH = "D:\\日语预习包\\学习资料\\我的用法.docx";
    static final String TIKA_URL = "http://localhost:9998/tika";
    static final String cmd_Curl = "curl";
    static final String cmd_Curl_Type = "-T";
    static final String cmd_Curl_Header = "Accept: text/plain";
    //java -jar D:\\chromeDownload\\chromeDownload\\tika-server-standard-2.4.1.jar --p 9998";
    //curl命令

    static String[] getCmdParts2 = {cmd_Curl, cmd_Curl_Type, ABSOLUATE_FILE_PATH, TIKA_URL, cmd_Curl_Header};


    /**
     * 使用ProcessBuilder 执行curl命令
     *
     * @param cmdParts 命令行
     * @return 解析字符
     */
//    public String tikaByCurl(String[] cmdParts);

    /**
     * 使用http请求tika
     *
     * @return 解析字符
     * @throws IOException 文件IO 异常
     */
    public Future<String> tikaByHttp() throws IOException, InterruptedException;
}
