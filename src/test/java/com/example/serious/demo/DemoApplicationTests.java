package com.example.serious.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import javax.xml.ws.Response;
import java.io.*;

@SpringBootTest
@Slf4j
class DemoApplicationTests extends Thread{

    private static final String ABSOLUATE_FILE_PATH = "D:\\日语预习包\\学习资料\\我的用法.docx";
    private static final String TIKA_URL = "http://localhost:9998/tika";
    private static final String cmd_Curl = "curl";
    private static final String cmd_Curl_Type = "-T";
    private static final String cmd_Curl_Header = "Accept: text/plain";
    //java -jar D:\\chromeDownload\\chromeDownload\\tika-server-standard-2.4.1.jar --p 9998";
    //curl命令

    static String[] getCmdParts2 = {cmd_Curl, cmd_Curl_Type, ABSOLUATE_FILE_PATH,TIKA_URL,cmd_Curl_Header};

    public static void main(String[] args) throws IOException {
        //http 文件流 请求tika
        log.info(tikaByHttp());
        //curl 文件路径 请求tika
        log.info(tikaByCurl(getCmdParts2));

    }

    /**
     * 使用ProcessBuilder 执行curl命令
     *
     * @param cmdParts 命令行
     * @return 解析字符
     */
    public static String tikaByCurl(String[] cmdParts){
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
     * 使用http请求tika
     *
     * @return 解析字符
     * @throws IOException 文件IO 异常
     */
    public static String tikaByHttp() throws IOException {
        HttpPut httpPut = new HttpPut(TIKA_URL);
        httpPut.setHeader(cmd_Curl_Header.split(":")[0],cmd_Curl_Header.split(":")[1]);
        httpPut.setEntity(new FileEntity(new File(ABSOLUATE_FILE_PATH)));
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse execute = closeableHttpClient.execute(httpPut);
        InputStream inputStream = execute.getEntity().getContent();
        byte[] b = new byte[100];
        StringBuilder stringBuilder = new StringBuilder();
        while(inputStream.read(b) != -1){
            String readString = new String(b);
            stringBuilder.append(readString);
        }
        return stringBuilder.toString();
    }
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Override
//    public void run() {
//        super.run();
//    }
//
//    public static String parseToPlainText() throws IOException, SAXException, TikaException {
//        String a = fileToTxt(new File("D:\\日语预习包\\学习资料\\我的用法.docx"));
//        System.out.println(a.trim());
//       return  a;
//    }
//
//    public static String fileToTxt(File f) {
//
//        Parser parser = new AutoDetectParser();
//
//        InputStream is = null;
//
//        try {
//
//            Metadata metadata = new Metadata();
//
//            is = new FileInputStream(f);
//
//            ContentHandler handler = new BodyContentHandler();
//
//            ParseContext context = new ParseContext();
//
//            context.set(Parser.class, parser);
//
//            parser.parse(is, handler, metadata, context);
//
//
//            return handler.toString();
//
//        } catch (FileNotFoundException e) {
//
//            e.printStackTrace();
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//
//        } catch (SAXException e) {
//
//            e.printStackTrace();
//
//        } catch (TikaException e) {
//
//            e.printStackTrace();
//
//        } finally {
//
//            if(is != null) {
//
//                try {
//
//                    is.close();
//
//                } catch (IOException e) {
//
//                    e.printStackTrace();
//
//                }
//
//            }
//
//        }
//
//        return null;
//
//    }
}
