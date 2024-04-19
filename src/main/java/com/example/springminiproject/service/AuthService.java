package com.example.springminiproject.service;

import com.example.springminiproject.model.request.ForgetPasswordRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    UserDetails loadUserByUsername(String email);

    void verifyOtpCode(Integer otpCode);

    void forgetPassword(String email,ForgetPasswordRequest forgetPasswordRequest);

    void resendCode(String email);
}
