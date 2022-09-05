package com.example.serious.demo.dao;

import com.example.serious.demo.entity.UserEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEsDao extends ElasticsearchRepository<UserEntity, Long> {
}
