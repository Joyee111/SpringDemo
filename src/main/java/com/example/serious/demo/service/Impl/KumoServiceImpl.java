package com.example.serious.demo.service.Impl;

import com.example.serious.demo.service.KumoService;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.ColorPalette;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class KumoServiceImpl implements KumoService {

    @Override
    public List<WordFrequency> load(FrequencyAnalyzer frequencyAnalyzer, File multipartFileToFile) throws IOException {
        return frequencyAnalyzer.load(multipartFileToFile);
    }

    @Override
    public List<WordFrequency> load(FrequencyAnalyzer frequencyAnalyzer, String filePath) throws IOException {
        return frequencyAnalyzer.load(filePath);
    }

    @Override
    public List<WordFrequency> load(FrequencyAnalyzer frequencyAnalyzer,List<String> list) throws IOException {
        return frequencyAnalyzer.load(list);
    }

    @Override
    public void getWordCloud(List<WordFrequency> wordFrequencyList, HttpServletResponse response, FrequencyAnalyzer frequencyAnalyzer) throws IOException {
        frequencyAnalyzer.setWordFrequenciesToReturn(600);
        frequencyAnalyzer.setMinWordLength(2);

        // 引入中文解析器
        frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());

        // 设置图片分辨率
        Dimension dimension = new Dimension(500, 500);
        // 此处的设置采用内置常量即可，生成词云对象
        WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        java.awt.Font font = new java.awt.Font("STSong-Light", 2, 18);
        wordCloud.setKumoFont(new KumoFont(font));
        wordCloud.setPadding(2);
        wordCloud.setColorPalette(new ColorPalette(new Color(0xed1941), new Color(0xf26522), new Color(0x845538),new Color(0x8a5d19),new Color(0x7f7522),new Color(0x5c7a29),new Color(0x1d953f),new Color(0x007d65),new Color(0x68DCA3)));
        wordCloud.setBackground(new CircleBackground(200));
        wordCloud.setFontScalar(new SqrtFontScalar(10, 40));
        wordCloud.setBackgroundColor(new Color(255, 255, 255));
        // 生成词云
        wordCloud.build(wordFrequencyList);
        OutputStream output = new ByteArrayOutputStream();
        wordCloud.writeToStream("png", output);
        byte[] outputByte = ((ByteArrayOutputStream)output).toByteArray();
        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(outputByte);

    }
}
