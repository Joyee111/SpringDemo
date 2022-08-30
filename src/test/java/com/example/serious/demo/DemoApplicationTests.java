package com.example.serious.demo;

import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class DemoApplicationTests extends Thread{

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) throws TikaException, IOException, SAXException {
        try{
            parseToPlainText();
        }catch (Exception e){

        }

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

}
