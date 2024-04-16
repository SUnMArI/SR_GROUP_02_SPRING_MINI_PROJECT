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
    public UserResponse findUserEmail(@RequestParam String email){
        return authService.findUserEmail(email);
    }

}
