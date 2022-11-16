package com.example.serious.demo.util.Functional;

import com.example.serious.demo.domain.User;

import java.util.function.Consumer;

@FunctionalInterface
public interface ConsumerService {
    void throwMessage(Consumer<User> message);

}
