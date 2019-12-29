package com.example.serious.demo.dao.Impl;

import com.example.serious.demo.dao.UserDao;
import com.example.serious.demo.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public int insert(User u1) {
        return 0;
    }
}
