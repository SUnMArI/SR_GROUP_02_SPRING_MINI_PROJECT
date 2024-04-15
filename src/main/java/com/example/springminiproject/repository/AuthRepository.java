package com.example.springminiproject.repository;

import com.example.springminiproject.model.Auth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface AuthRepository {
    @Select("""
    SELECT * FROM user_tb WHERE email = #{email}
    
""")
    @Result(property = "userId",column = "user_id")
    @Result(property = "profileImage",column = "profile_image")
    Auth getUserByEmail(String email);
}
