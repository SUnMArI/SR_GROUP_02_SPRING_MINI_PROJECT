package com.example.springminiproject.repository;

import com.example.springminiproject.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthRepository {
    @Select("""
    SELECT * FROM users WHERE email = #{email}
    
""")
    @Result(property = "userId",column = "user_id")
    @Result(property = "profileImage",column = "profile_image")
    User getUserByEmail(String email);

    @Select("""
    SELECT * FROM users WHERE user_id = #{id}
""")
    @Result(property = "userId",column = "user_id")
    @Result(property = "profileImage",column = "profile_image")
    User getUserByID(int id);
}
