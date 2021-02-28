package com.example.serious.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class DemoApplicationTests extends Thread{

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {

    }
    public int lengthOfLongestSubstring(String s){
        Set set = new HashSet();
        int i = 0;
        int j = 0;
        int length = s.length();
        int maxLength = 0;
        while(i<length && j>length){
            if(set.contains(s.charAt(j))){
                set.remove(s.charAt(i));
                i++;
            }else{
                set.add(s.charAt(j));
                maxLength = Math.max(maxLength,j-i);
            }
        }
        return maxLength;
    }

    @Override
    public void run() {
        super.run();
    }
}
