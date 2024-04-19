package com.example.springminiproject.service;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {
     void send(String email,String otp) throws MessagingException, UnsupportedEncodingException ;
}
