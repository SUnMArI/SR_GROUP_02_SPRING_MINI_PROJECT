package com.example.springminiproject.repository;


import com.example.springminiproject.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.UUID;

@Mapper
public interface AuthRepository {
    @Results(id = "authMapper" , value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "profileImage", column = "profile_image")
    })

    @Select("""
    SELECT * FROM users WHERE email = #{email}
""")
    User getUserByEmail(String email);
}
