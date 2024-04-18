package com.example.springminiproject.service.serviceImplement;

import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.model.Otps;
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
        }
        else {
            Random random = new Random();
            String  otp = String.valueOf(random.nextInt(999999));
            LocalDateTime issuedAt = LocalDateTime.now();
            LocalDateTime expiration = issuedAt.minusDays(1);
            boolean verify = true;
            Integer userId = user.getUserId();
            authRepository.insertOtpCode(otp, issuedAt,expiration,verify,userId);
            return modelMapper.map(user, UserResponse.class);
        }
    }
//
//    @Override
//    public UserResponse findOtpCode(String opt_code){
//        Otps user = authRepository.findOtpCode(opt_code);
//        if(user == null){
//            throw  new NotFoundException("This optCode " + opt_code + " not found");
//        }
//        else {
//            Random random = new Random();
//            String  otp = String.valueOf(random.nextInt(999999));
////            LocalDateTime issuedAt = LocalDateTime.now();
////            LocalDateTime expiration = issuedAt.minusDays(1);
//            boolean verify = true;
////            Integer userId = user.getUserId();
////            authRepository.insertOtpCode(otp, issuedAt,expiration,verify,userId);
//              authRepository.inpput(otp);
//            return modelMapper.map(user, UserResponse.class);
//        }
//    }
}
