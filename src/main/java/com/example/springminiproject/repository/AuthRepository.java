package com.example.springminiproject.repository;

import com.example.springminiproject.exception.UUIDTypeHandler;
import com.example.springminiproject.model.dto.request.AppUserRequest;
import com.example.springminiproject.model.dto.response.AppUserResponse;
import org.apache.ibatis.annotations.*;

import java.util.UUID;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface AuthRepository {
    @Results(
            id = "userMapper" , value = {
                @Result(property = "userId",column = "user_id"),
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
    Boolean duplicateEmail(String email);

}
