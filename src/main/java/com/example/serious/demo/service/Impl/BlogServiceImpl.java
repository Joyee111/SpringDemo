package com.example.serious.demo.service.Impl;

import com.example.serious.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class BlogServiceImpl implements BlogService {
    @Override
    public Map<String, String> getBlogInfo(String userId) {
        return null;
    }
}
