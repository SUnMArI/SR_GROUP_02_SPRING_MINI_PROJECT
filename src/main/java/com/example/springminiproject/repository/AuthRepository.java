package com.example.springminiproject.repository;

import com.example.springminiproject.model.User;
import com.example.springminiproject.model.dto.request.AppUserRequest;
import com.example.springminiproject.model.dto.response.AppUserResponse;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AuthRepository {
    @Results(
            id = "userMapper" , value = {
                @Result(property = "user",column = "user_id"),
                @Result(property = "profileImage",column = "profile_image"),
            }
    )
    @Select("""
        INSERT INTO users VALUES (default,#{user.email},#{user.password},#{user.profileImage}) RETURNING *
    """)
    AppUserResponse register(@Param("user") AppUserRequest appUserRequest);

    @Select("""
        SELECT * FROM users WHERE email = #{email}
    """)
    @Result(property = "userId",column = "user_id")
    User getUserByEmail(String email);

    @Select("""
        SELECT * FROM users WHERE user_id = #{userId}
    """)
    @Result(property = "userId",column = "user_id")
    @Result(property = "email",column = "email")
    @Result(property = "profileImage",column = "profile_image")
    AppUserResponse getUserById(@Param("uerId") Integer userId);
}
