package com.example.serious.demo.controller;

import com.example.serious.demo.service.KumoService;
import com.example.serious.demo.service.TikaService;
import com.example.serious.demo.util.FileUtil;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author joyee
 */
@Controller
@RequestMapping("/kumo")
public class KumoController {

    @Autowired
    private KumoService kumoService;

    @Autowired
    private TikaService tikaService;

    @RequestMapping("/file")
    public void generatePng(MultipartFile file, HttpServletResponse response) throws IOException, InterruptedException, ExecutionException {
        List<String> list = tikaService.tikaSynFileForKumo(FileUtil.multipartFileToFile(file));
        FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        kumoService.getWordCloud(kumoService.load(frequencyAnalyzer,list),response,frequencyAnalyzer);
    }

    public static int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int[][] arr = new int[increase.length + 1][3];
        for (int i = 1; i <= increase.length; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = arr[i - 1][j] + increase[i - 1][j];
            }
        }
        int[] result = new int[requirements.length];
        for (int i = 0; i < requirements.length; i++) {
            int idx = 0;
            for (int j = 0; j < 3; j++) {
                idx = getIdx(arr, idx, j, requirements[i][j]);
            }
            result[i] = idx;
        }
        return result;
    }

    public static int getIdx(int[][] arr, int l, int index, int target) {
        if (l == -1) {
            return -1;
        }
        int idx = -1;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid][index] >= target) {
                idx = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return idx;
    }
}
