package com.example.springminiproject.repository;

import com.example.springminiproject.model.dto.Otps;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;

@Mapper
public interface OtpRepository {
    @Results(id = "otpMap",value = {
            @Result(property = "otpId", column = "otp_id"),
            @Result(property = "otpCode", column = "otp_code"),
            @Result(property = "issuedAt", column = "issued_at"),
            @Result(property = "expiration", column = "expiration"),
            @Result(property = "verify", column = "verify")

    })
    @Select("""
    SELECT * FROM otps WHERE otp_code = #{otpCode}
""")
    Otps findOtpByOtpCode(Integer otpCode);

    @Update("""
    UPDATE otps SET verify = #{b} WHERE otp_code=#{otpCode}
""")
    void setVerify(boolean b, Integer otpCode);

    @Insert("""
    INSERT INTO otps (otp_code,issued_at,expiration,verify,user_id) VALUES (#{otpCode},#{issuedAt},#{expiration}, #{b},#{userId})
""")
    void insert(Integer otpCode,  LocalDateTime issuedAt, LocalDateTime expiration, boolean b, Integer userId);
}
