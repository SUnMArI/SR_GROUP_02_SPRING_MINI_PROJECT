package com.example.springminiproject.repository;

import com.example.springminiproject.model.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AuthRepository {
    @Select("""
    SELECT * FROM users WHERE email = #{email}
    
""")
    @Result(property = "userId",column = "user_id")
    @Result(property = "profileImage",column = "profile_image")
    User getUserByEmail(String email);

    @Update("""
    UPDATE users SET password = #{confirmPassword} WHERE email = #{email}
""")
    void updatePassword(String email, String confirmPassword);
}
