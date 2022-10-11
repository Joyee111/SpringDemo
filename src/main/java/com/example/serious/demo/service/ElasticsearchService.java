package com.example.serious.demo.service;

import com.example.serious.demo.entity.FileEntity;

public interface ElasticsearchService {

    void save(FileEntity fileEntity);
}
