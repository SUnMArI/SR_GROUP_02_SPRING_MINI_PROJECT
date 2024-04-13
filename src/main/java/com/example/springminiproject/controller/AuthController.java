package com.example.springminiproject.controller;

import com.example.springminiproject.model.dto.request.AppUserRequest;
import com.example.springminiproject.model.dto.response.AppUserResponse;
import com.example.springminiproject.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/register")
    public ResponseEntity<AppUserResponse> register(@RequestBody AppUserRequest appUserRequest){
        return ResponseEntity.ok(authService.register(appUserRequest));
    }
}
