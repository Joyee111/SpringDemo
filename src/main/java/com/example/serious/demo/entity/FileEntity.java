package com.example.serious.demo.entity;

import com.example.serious.demo.mq.core.stream.AbstractStreamMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "file")
@Accessors(chain = true)
public class FileEntity {
    @Id
    private String id;
    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_smart", fielddata = true)
    private String fileName;
    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_smart", fielddata = true)
    private String content;
    @Field(type = FieldType.Date, format = DateFormat.year_month_day)
    private Date creaTime;

}

