package com.example.springminiproject.service.serviceImplement;

import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.model.User;
import com.example.springminiproject.model.dto.response.UserResponse;
import com.example.springminiproject.repository.AuthRepository;
import com.example.springminiproject.service.AuthService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@AllArgsConstructor
@Service
public class AuthServiceImp implements AuthService {
    private final AuthRepository authRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserResponse findUserEmail(String email) {
        User user = authRepository.findUserEmail(email);
        if(user == null){
            throw new NotFoundException("Email " + email + " not found");
        } else {
            String otp = generateOTP();
            LocalDateTime issuedAt = LocalDateTime.now();
            LocalDateTime expiration = issuedAt.minusDays(1);
            boolean verify = false;
            Integer userId = user.getUserId();
            authRepository.insertOtpCode(otp, issuedAt,expiration,verify,userId);
            return modelMapper.map(user, UserResponse.class);
        }
    }
    private String generateOTP(){
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}
