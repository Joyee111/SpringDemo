package com.example.serious.demo.domain;

import java.util.Date;

public class Blog {
    private Integer id;
    private String title;
    private Date s_atime;
    private String author;
    private String description;
    private String imgUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getId() {
        return id;
    }

    public Date getS_atime() {
        return s_atime;
    }

    public void setS_atime(Date s_atime) {
        this.s_atime = s_atime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
