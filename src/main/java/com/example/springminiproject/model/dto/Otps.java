package com.example.springminiproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Otps {
    private int otpId;
    private int otpCode;
    private LocalDateTime issuedAt;
    private LocalDateTime expiration;
    private Boolean verify ;
}
