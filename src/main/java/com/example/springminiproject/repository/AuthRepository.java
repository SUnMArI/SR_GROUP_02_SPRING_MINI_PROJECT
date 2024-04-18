package com.example.springminiproject.repository;

import com.example.springminiproject.model.Otps;
import com.example.springminiproject.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDateTime;

@Mapper
public interface AuthRepository{

    // Mapping
    @Results(id = "UserMapper" , value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "profileImage", column = "profile_image")
    })

    // Search Email Users
    @Select("""
            SELECT * FROM users WHERE email = #{email}
        """)
    User findUserEmail(String email);


    // Insert to otps_table
    @Insert("""
            INSERT INTO otps (opt_code, issued_at,expiration, verify, user_id) VALUES (#{opt_code}, #{issued_at}, #{expiration}, #{verify}, #{user_id}) RETURNING *
        """)
    void insertOtpCode(@Param("opt_code") String otpCode,
                       @Param("issued_at") LocalDateTime issuedAt,
                       @Param("expiration") LocalDateTime expiration,
                       @Param("verify") boolean verify,
                       @Param("user_id") Integer userId);
//    // Verify
//    @Select("""
//        SELECT * FROM otps WHERE opt_code = #{optCode}
//        """)
//    Otps findOtpCode(String optCode);
//
//    // Insert to otps_table
//    @Select("""
//            UPDATE otps SET opt_code  = (#{opt_code}) RETURNING *
//        """)
//    void inpput(@Param("opt_code") String otpCode);

}
