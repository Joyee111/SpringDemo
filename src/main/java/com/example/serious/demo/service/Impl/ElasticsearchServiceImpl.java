package com.example.serious.demo.service.Impl;

import com.example.serious.demo.entity.FileEntity;
import com.example.serious.demo.service.ElasticsearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ElasticsearchServiceImpl implements ElasticsearchService {
    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void save(FileEntity fileEntity) {
//        拿到自增的返回值
        IndexQuery indexQuery = new IndexQueryBuilder()
//                .withId(esEntity.getId())
                .withObject(fileEntity)
                .build();
        try {
            elasticsearchRestTemplate.index(indexQuery, IndexCoordinates.of("file"));
        } catch (Exception e) {
            log.info(e.getMessage());
        }

    }
}
