package com.example.serious.demo.dao;


import com.example.serious.demo.domain.Blog;

import java.util.List;

public interface BlogDao {
    public Blog getAllBlogInfo();

    public Blog getBlog();

    public int setBlog(Blog blog);

    List<Blog> findAllBlog();
}
