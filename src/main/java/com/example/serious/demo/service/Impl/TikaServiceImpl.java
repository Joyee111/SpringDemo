package com.example.serious.demo.service.Impl;

import com.alibaba.fastjson.JSON;
import com.example.serious.demo.service.ElasticsearchService;
import com.example.serious.demo.service.TikaService;
import com.example.serious.demo.util.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

@Service
@Slf4j
public class TikaServiceImpl implements TikaService {

    @Autowired
    private ElasticsearchService elasticsearchService;

    /**
     * 使用ProcessBuilder 执行curl命令
     *
     * @param cmdParts 命令行
     * @return 解析字符
     */
    public String tikaByCurl(String[] cmdParts) {
        ProcessBuilder process = new ProcessBuilder(cmdParts);
        Process p;
        try {
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            return builder.toString();
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return null;
    }

    /**
     * 使用http请求tika 异步回调结果
     *
     * @return 解析字符
     * @throws IOException 文件IO 异常
     */
    @Async
    @Override
    public Future<String> tikaByHttp(MultipartFile file, String fileName) throws IOException, InterruptedException {
        Map<String, String> result = new HashMap<>();
        result.put("usercode", "joyee");
        result.put("status", "DOING");
        WebSocket.sendMessageTo(JSON.toJSONString(result));
        HttpPut httpPut = new HttpPut(TIKA_URL);
        httpPut.setHeader(cmd_Curl_Header.split(":")[0], cmd_Curl_Header.split(":")[1]);
        httpPut.setEntity(new FileEntity(convert(file, fileName)));
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse execute = closeableHttpClient.execute(httpPut);
        InputStream inputStream = execute.getEntity().getContent();
        byte[] b = new byte[100];
        StringBuilder stringBuilder = new StringBuilder();
        while (inputStream.read(b) != -1) {
            String readString = new String(b);
            stringBuilder.append(readString);
        }
        Thread.sleep(3000);
        result.put("status", "DONE");
        result.put("analyseContent", stringBuilder.toString());
        com.example.serious.demo.entity.FileEntity fileEntity = new com.example.serious.demo.entity.FileEntity();
        fileEntity.setFileName(fileName)
                .setContent(stringBuilder.toString())
                .setCreaTime(new Date());

        elasticsearchService.save(fileEntity);
        //TODO 入库操作
        //TODO 发通知
        return new AsyncResult<>(WebSocket.sendMessageTo(JSON.toJSONString(result)));
    }

    private File convert(MultipartFile file, String fileName) {
        String fileOriginalFilename = file.getOriginalFilename();
        File newFile = new File(fileName);
        OutputStream out = null;
        try {
            //获取文件流，以文件流的方式输出到新文件
//    InputStream in = multipartFile.getInputStream();
            out = new FileOutputStream(newFile);
            byte[] ss = new byte[1024];
            InputStream inputStream = file.getInputStream();
            while (inputStream.read() != -1) {
                inputStream.read(ss);
                for (int i = 0; i < ss.length; i++) {
                    out.write(ss[i]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return newFile;
    }

}
