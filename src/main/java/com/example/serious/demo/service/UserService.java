package com.example.serious.demo.service;

import com.example.serious.demo.domain.User;

public interface UserService {
    public User getById(int id);
    public boolean tx();
}
