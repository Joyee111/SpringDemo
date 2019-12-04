package com.example.serious.demo.service.Impl;

import com.example.serious.demo.dao.UserDao;
import com.example.serious.demo.domain.User;
import com.example.serious.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public boolean tx() {
        User u1 = new User();
        u1.setId(3);
        u1.setName("haha");
        userDao.insert(u1);

        User u2 = new User();
        u2.setId(1);
        u2.setName("just");
        userDao.insert(u2);
        return true;
    }
}
