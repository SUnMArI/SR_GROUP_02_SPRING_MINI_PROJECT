package com.example.springminiproject.repository;

import com.example.springminiproject.exception.UUIDTypeHandler;
import com.example.springminiproject.model.dto.User;
import com.example.springminiproject.model.request.AppUserRequest;
import com.example.springminiproject.model.response.AppUserResponse;
import org.apache.ibatis.annotations.*;

import java.util.UUID;
import com.example.springminiproject.model.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AuthRepository {
    @Results(
            id = "userMapper" , value = {
                @Result(property = "userId",column = "user_id",javaType = UUID.class,typeHandler = UUIDTypeHandler.class),
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




    @Update("""
    UPDATE users SET password = #{confirmPassword} WHERE email = #{email}
""")
    void updatePassword(String email, String confirmPassword);

    @Select("""
        SELECT * FROM users WHERE user_id = CAST(#{userId} AS UUID)
    """)
    @Result(property = "userId",column = "user_id")
    @Result(property = "email",column = "email")
    @Result(property = "profileImage",column = "profile_image")
    AppUserResponse getUserById(@Param("uerId") UUID userId);
}
