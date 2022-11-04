package com.example.serious.demo.service;

import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface KumoService {

    List<WordFrequency>  load(FrequencyAnalyzer frequencyAnalyzer, File multipartFileToFile) throws IOException;
    List<WordFrequency>  load(FrequencyAnalyzer frequencyAnalyzer, String word) throws IOException;
    List<WordFrequency>  load(FrequencyAnalyzer frequencyAnalyzer, List<String> list) throws IOException;

    void getWordCloud(List<WordFrequency> wordFrequencyList, HttpServletResponse response, FrequencyAnalyzer frequencyAnalyzer) throws IOException;
}
