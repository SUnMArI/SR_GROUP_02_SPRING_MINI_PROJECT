package com.example.springminiproject.controller;

import com.example.springminiproject.model.apiResponse.APIResponse;
import com.example.springminiproject.model.dto.response.UserResponse;
import com.example.springminiproject.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/v1/auth/resend")
    public UserResponse resendCode(@RequestParam String email){
        return authService.findUserEmail(email);
    }
//
//    @PutMapping("/api/v1/auth/verify")
//    public UserResponse response(@RequestParam String opt_code){
//        return authService.findOtpCode(opt_code);
//    }

}
