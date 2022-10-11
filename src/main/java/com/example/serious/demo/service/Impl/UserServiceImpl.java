package com.example.serious.demo.service.Impl;

import com.example.serious.demo.domain.User;
import com.example.serious.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public boolean tx() {
        return false;
    }
}
