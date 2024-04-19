package com.example.springminiproject.service;

import com.example.springminiproject.model.request.AppUserRequest;
import com.example.springminiproject.model.response.AppUserResponse;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    AppUserResponse register(AppUserRequest appUserRequest) throws NotFoundException;

}
