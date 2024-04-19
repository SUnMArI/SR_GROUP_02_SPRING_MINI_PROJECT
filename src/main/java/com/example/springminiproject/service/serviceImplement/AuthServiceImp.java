package com.example.springminiproject.service.serviceImplement;

import com.example.springminiproject.model.dto.User;
import com.example.springminiproject.repository.AuthRepository;
import com.example.springminiproject.service.AuthService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthServiceImp implements AuthService {
    private final AuthRepository authRepository;
    private final ModelMapper modelMapper;
    @Override
    public UserDetails loadUserByUsername(String email) {
        User auth= authRepository.getUserByEmail(email);
        System.out.println("auth"+auth.toString());
        return modelMapper.map(auth, User.class);
    }

}
