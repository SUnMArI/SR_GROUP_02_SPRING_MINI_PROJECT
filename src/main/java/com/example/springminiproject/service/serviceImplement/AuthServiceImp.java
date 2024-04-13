package com.example.springminiproject.service.serviceImplement;

import com.example.springminiproject.repository.AuthRepository;
import com.example.springminiproject.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthServiceImp implements AuthService {
    private final AuthRepository authRepository;
}
