package com.example.springminiproject.controller;

import com.example.springminiproject.model.dto.request.AppUserRequest;
import com.example.springminiproject.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid AppUserRequest appUserRequest) throws NotFoundException {
        return ResponseEntity.ok(authService.register(appUserRequest));
    }
}
