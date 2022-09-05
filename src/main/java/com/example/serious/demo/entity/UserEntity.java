package com.example.serious.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "user", shards = 3, replicas = 1, refreshInterval = "30s")
public class UserEntity {
    @Id
    private String id;
    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_smart", fielddata = true)
    private String name;
    @Field(type = FieldType.Keyword)
    private String sex;
    @Field(type = FieldType.Integer)
    private Integer age;
    @Field(type = FieldType.Keyword, index = false)
    private String grade;
}

