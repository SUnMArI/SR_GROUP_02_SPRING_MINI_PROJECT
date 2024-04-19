package com.example.springminiproject.service.serviceImplement;

import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.exception.PasswordException;
import com.example.springminiproject.exception.ValidationException;
import com.example.springminiproject.model.User;
import com.example.springminiproject.model.User;
import com.example.springminiproject.model.dto.request.AppUserRequest;
import com.example.springminiproject.model.dto.response.AppUserResponse;
import com.example.springminiproject.repository.AuthRepository;
import com.example.springminiproject.service.AuthService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@Service
public class AuthServiceImp implements AuthService {
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;
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
        return modelMapper.map(auth, User.class);
    }

}
