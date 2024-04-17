package com.example.springminiproject.service.serviceImplement;

import com.example.springminiproject.model.request.RequestPassword;
import com.example.springminiproject.repository.AuthRepository;
import com.example.springminiproject.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthServiceImp implements AuthService {
    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return authRepository.getUserByEmail(email);
    }

    @Override
    public void forgetPassword(String email, RequestPassword requestPassword) {

    }
}
