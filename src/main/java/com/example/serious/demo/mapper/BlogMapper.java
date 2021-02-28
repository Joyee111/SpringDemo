package com.example.serious.demo.mapper;

import com.example.serious.demo.domain.Blog;
import com.example.serious.demo.domain.TestEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogMapper {

    @Insert("insert into joyeeblog.sy_comm_blog(title,s_atime,author,description,imgurl) values(#{title},#{s_atime},#{author},#{description},#{imgUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertBlog(Blog blog);

    @Select("select * from joyeeblog.sy_comm_blog order by s_atime desc")
    List<Blog> findAllBlog(TestEntity testEntity);
    @Insert("insert into joyeeblog.single_table(key1,key2,key3,key_part1,key_part2,key_part3,common_field ) values (#{key1},#{key2},#{key3},#{key_part1},#{key_part2},#{key_part3},#{common_field})")
    int insertData(TestEntity testEntity);

    List<Blog> findAllBlog();
}
