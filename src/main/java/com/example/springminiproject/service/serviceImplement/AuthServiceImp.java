package com.example.springminiproject.service.serviceImplement;

import com.example.springminiproject.model.dto.request.AppUserRequest;
import com.example.springminiproject.model.dto.response.AppUserResponse;
import com.example.springminiproject.repository.AuthRepository;
import com.example.springminiproject.service.AuthService;
import jakarta.servlet.http.HttpServlet;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Currency;

@AllArgsConstructor
@Service
public class AuthServiceImp implements AuthService {
    private final AuthRepository authRepository;

    @Override
    public AppUserResponse register(AppUserRequest appUserRequest) {
        if(appUserRequest.getPassword().equals(appUserRequest.getConfirmPassword())){
            return authRepository.register(appUserRequest);
        }
        return null;
    }
}
