package com.example.serious.demo.service;

import com.example.serious.demo.domain.UserEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<UserEs,String> {
    List<UserEs> findByName(String name);

    List<UserEs> findByAddress(String address);
}
