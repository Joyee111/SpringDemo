package com.example.serious.demo.mapper;

import com.example.serious.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from joyeeblog.sy_org_user where user_code = #{id}")
    User queryUserById(@Param(value = "id") int id);
}
