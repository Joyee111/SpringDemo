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

    @Test
    void contextLoads() {
    }
    //static String[] cmdParts1 = {"java -jar D:\\chromeDownload\\chromeDownload\\tika-server-standard-2.4.1.jar --p 1234"};
    static String[] getCmdParts2 = {"curl", "-T", "D:\\日语预习包\\学习资料\\我的用法.docx","http://localhost:9998/tika","Accept: text/plain"};


    public static void main(String[] args) throws TikaException, IOException, SAXException {
        //log.info(toTika());
        log.info(tikaByCurl(getCmdParts2));

    }
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
            System.out.print("error");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void run() {
        super.run();
    }


    public static String parseToPlainText() throws IOException, SAXException, TikaException {
//        BodyContentHandler handler = new BodyContentHandler();
//
//        AutoDetectParser parser = new AutoDetectParser();
//        Metadata metadata = new Metadata();
//        try (InputStream stream = ParsingExample.class.getResourceAsStream("test.doc")
//
//        ) {
//            parser.parse(stream, handler, metadata);
//            return handler.toString();
//        }
        String a = fileToTxt(new File("D:\\日语预习包\\学习资料\\我的用法.docx"));
        System.out.println(a.trim());
       return  a;
    }

    public static String fileToTxt(File f) {

        Parser parser = new AutoDetectParser();

        InputStream is = null;

        try {

            Metadata metadata = new Metadata();

            is = new FileInputStream(f);

            ContentHandler handler = new BodyContentHandler();

            ParseContext context = new ParseContext();

            context.set(Parser.class, parser);

            parser.parse(is, handler, metadata, context);


            return handler.toString();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (SAXException e) {

            e.printStackTrace();

        } catch (TikaException e) {

            e.printStackTrace();

        } finally {

            if(is != null) {

                try {

                    is.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }

        return null;

    }


    public static String toTika() throws IOException {
        String url = "http://localhost:9998/tika";
        HttpPut httpPut = new HttpPut(url);
        httpPut.setHeader("Accept","text/plain");
        httpPut.setEntity(new FileEntity(new File("D:\\上理视频\\操作系统课后题答案.docx")));
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
}
