package com.example.springminiproject.service;

import com.example.springminiproject.model.dto.request.AppUserRequest;
import com.example.springminiproject.model.dto.response.AppUserResponse;

public interface AuthService {
    AppUserResponse register(AppUserRequest appUserRequest);
}
