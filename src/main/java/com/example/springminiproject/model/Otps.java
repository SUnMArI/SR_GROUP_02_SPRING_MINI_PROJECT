package com.example.springminiproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Otps {
    private Integer otpId;
    private String otpCode;
    private Timestamp issuedAt;
    private Timestamp expiration;
    private Boolean verify;
    private Integer userId;
}
