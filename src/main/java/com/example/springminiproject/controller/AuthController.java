package com.example.springminiproject.controller;

import com.example.springminiproject.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;
}
