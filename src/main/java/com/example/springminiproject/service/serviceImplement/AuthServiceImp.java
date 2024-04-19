package com.example.springminiproject.service.serviceImplement;

import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.exception.PasswordException;
import com.example.springminiproject.exception.ValidationException;
import com.example.springminiproject.model.dto.User;
import com.example.springminiproject.model.request.AppUserRequest;
import com.example.springminiproject.model.response.AppUserResponse;
import com.example.springminiproject.exception.NoContentException;
import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.exception.TimeoutOptCodeException;
import com.example.springminiproject.model.dto.Otps;
import com.example.springminiproject.model.dto.User;
import com.example.springminiproject.model.request.ForgetPasswordRequest;
import com.example.springminiproject.repository.AuthRepository;
import com.example.springminiproject.repository.OtpRepository;
import com.example.springminiproject.service.AuthService;
import com.example.springminiproject.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSendException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Random;

@AllArgsConstructor
@Service
public class AuthServiceImp implements AuthService {
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final OtpRepository otpRepository;
    private final ModelMapper modelMapper;
    private final EmailService emailService;

    @Override
    public AppUserResponse register(AppUserRequest appUserRequest){
        User user = authRepository.getUserByEmail(appUserRequest.getEmail());
        if(user!=null){
            throw new NotFoundException("This email already register !!!");
        }
        if(!appUserRequest.getPassword().equals(appUserRequest.getConfirmPassword())){
            throw new PasswordException("Your confirm password does not match with your password");
        }
        if(
                appUserRequest.getProfileImage().contains(".jpg") ||
                appUserRequest.getProfileImage().contains(".png") ||
                appUserRequest.getProfileImage().contains(".bmp") ||
                appUserRequest.getProfileImage().contains(".gif")
        ){
            appUserRequest.setPassword(bCryptPasswordEncoder.encode(
                    appUserRequest.getPassword()
            ));
            return authRepository.register(appUserRequest);
        }else{
            Map<String, String> errors = new HashMap<>();
            errors.put("profileImage", "profile must be contain file extension such as jpg, png, gif, and bmp only");
            throw new ValidationException(HttpStatus.BAD_REQUEST, "Validate Invalid", errors);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User auth= authRepository.getUserByEmail(email);
        System.out.println("auth"+auth.toString());
        return modelMapper.map(auth, User.class);
    }

    @Override
    public void resendCode(String email) {
        User user = authRepository.getUserByEmail(email);
        if (user==null){
            throw new NotFoundException("This email haven't register yet");
        }
        Random random = new Random();
        Integer otpCode = random.nextInt(999999);
        try {
            emailService.send(email,String.valueOf(otpCode));
            otpRepository.insert(otpCode,LocalDateTime.now(),LocalDateTime.now().plusMinutes(1),false,user.getUserId());
        }catch (MessagingException | UnsupportedEncodingException | MailSendException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void verifyOtpCode(Integer otpCode) {
         Otps otps= otpRepository.findOtpByOtpCode(otpCode);

         if(otps==null){
             throw new NotFoundException("Otp Code incorrect");
         } else if (otps.getExpiration().isBefore(LocalDateTime.now())) {
             throw new TimeoutOptCodeException("Otp Code already expire");
         }else {
             otpRepository.setVerify(true,otps.getOtpCode());
         }
    }

    @Override
    public void forgetPassword(String email, ForgetPasswordRequest forgetPasswordRequest) {

            if(forgetPasswordRequest.getPassword().compareTo(forgetPasswordRequest.getConfirmPassword())!=0){
                throw new NoContentException("Confirm Password doesn't match to Curren Password");
            }else {
                authRepository.updatePassword(email,bCryptPasswordEncoder.encode(forgetPasswordRequest.getConfirmPassword()));
            }
    }

}
