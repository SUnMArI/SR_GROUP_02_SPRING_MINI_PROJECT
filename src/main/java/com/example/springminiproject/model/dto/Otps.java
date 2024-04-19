package com.example.springminiproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Otps {
    private UUID otpId;
    private int otpCode;
    private LocalDateTime issuedAt;
    private LocalDateTime expiration;
    private Boolean verify ;
}
