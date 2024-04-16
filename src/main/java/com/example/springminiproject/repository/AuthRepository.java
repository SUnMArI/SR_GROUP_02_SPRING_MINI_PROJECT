package com.example.springminiproject.repository;

import com.example.springminiproject.model.User;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;

@Mapper
public interface AuthRepository{

    // Post Resend

    @Select("""
            SELECT * FROM users WHERE email = #{email}
        """)
    User findUserEmail(String email);


    @Insert("""
            INSERT INTO otps (opt_code, issued_at,expiration, verify, user_id) VALUES (#{opt_code}, #{issued_at}, #{expiration}, #{verify}, #{user_id}) RETURNING *
        """)
    void insertOtpCode(@Param("opt_code") String otpCode,
                       @Param("issued_at") LocalDateTime issuedAt,
                       @Param("expiration") LocalDateTime expiration,
                       @Param("verify") boolean verify,
                       @Param("user_id") Integer userId);

}
