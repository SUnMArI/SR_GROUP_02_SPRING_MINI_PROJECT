package com.example.springminiproject.service;

import com.example.springminiproject.model.dto.request.AppUserRequest;
import com.example.springminiproject.model.dto.request.PasswordRequest;
import com.example.springminiproject.model.dto.response.AppUserResponse;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    AppUserResponse register(AppUserRequest appUserRequest) throws NotFoundException;

}
