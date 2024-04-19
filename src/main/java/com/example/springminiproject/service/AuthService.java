package com.example.springminiproject.service;

import com.example.springminiproject.model.request.AppUserRequest;
import com.example.springminiproject.model.response.AppUserResponse;
import org.apache.ibatis.javassist.NotFoundException;
import com.example.springminiproject.model.request.ForgetPasswordRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    AppUserResponse register(AppUserRequest appUserRequest) throws NotFoundException;

    UserDetails loadUserByUsername(String email);

    void verifyOtpCode(Integer otpCode);

    void forgetPassword(String email,ForgetPasswordRequest forgetPasswordRequest);

    void resendCode(String email);
}
