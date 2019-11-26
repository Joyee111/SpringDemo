package com.example.serious.demo.dao.Impl;

import com.example.serious.demo.controller.indexPage;
import com.example.serious.demo.dao.BlogDao;
import com.example.serious.demo.po.Blog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class BlogDaoImpl implements BlogDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogDaoImpl.class);
    @Override
    public Blog getAllBlogInfo() {
        return null;
    }

    @Override
    public Blog getBlog() {
        return null;
    }
}
