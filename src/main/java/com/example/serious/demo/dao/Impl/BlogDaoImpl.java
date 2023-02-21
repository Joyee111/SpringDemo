package com.example.serious.demo.dao.Impl;

import com.example.serious.demo.dao.BlogDao;
import com.example.serious.demo.domain.Blog;
import com.example.serious.demo.domain.TestEntity;
import com.example.serious.demo.mapper.BlogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BlogDaoImpl implements BlogDao {
    @Autowired
    BlogMapper blogMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogDaoImpl.class);
    @Override
    public Blog getAllBlogInfo() {
        return null;
    }

    @Override
    public Blog getBlog() {
        System.out.println("继续执行");
        return null;
    }

    @Override
    public int setBlog(Blog blog) {
        int result = 0;
        try{
            result = blogMapper.insertBlog(blog);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Blog> findAllBlog() {

        return blogMapper.findAllBlog();
    }

    @Override
    public int insertData(TestEntity testEntity) {
        int result = 0;
        try{
            result = blogMapper.insertData(testEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


}
