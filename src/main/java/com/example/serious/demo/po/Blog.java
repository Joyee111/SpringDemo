package com.example.serious.demo.po;

import java.util.Date;

public class Blog {
    private String title;
    private Date date;
    private String userId;
    private String userName;
    private String introducation;
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIntroducation() {
        return introducation;
    }

    public void setIntroducation(String introducation) {
        this.introducation = introducation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", introducation='" + introducation + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
