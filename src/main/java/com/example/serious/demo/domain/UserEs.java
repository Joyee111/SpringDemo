package com.example.serious.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * 用在类上作用:将Emp的对象映射成ES中一条json格式文档
 * indexName: 用来指定这个对象的转为json文档存入那个索引中 要求:ES服务器中之前不能存在此索引名
 * type     : 用来指定在当前这个索引下创建的类型名称
 *
 * @Author Christy
 * @Date 2021/4/29 21:22
 */
@Data
@Document(indexName = "christy",type = "user")
public class UserEs {
    @Id //用来将对象中id属性与文档中_id 一一对应
    private String id;

    // 用在属性上 代表mapping中一个属性 一个字段 type:属性 用来指定字段类型 analyzer:指定分词器
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;

    @Field(type = FieldType.Integer)
    private Integer age;

    @Field(type = FieldType.Date)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bir;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String content;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String address;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String introduce;

}
