package com.example.springminiproject.service;

import com.example.springminiproject.model.dto.response.UserResponse;

public interface AuthService {

    UserResponse findUserEmail(String email);


}
