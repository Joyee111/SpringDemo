package com.example.serious.demo.dao;

import com.example.serious.demo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Select("select*from user where id=#{id}")
    public User getById(@Param("id")int id);
    @Insert("insert into user(id,name)values(#{id},#{name})")
    public int insert(User u1);

}
