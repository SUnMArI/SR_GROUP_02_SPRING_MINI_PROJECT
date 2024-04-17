package com.example.springminiproject.service;

import com.example.springminiproject.model.request.RequestPassword;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

     UserDetails loadUserByUsername(String username) ;


     void forgetPassword(String email, RequestPassword requestPassword);
}
