package com.example.springminiproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private UUID userId;
    private String email;
    private String password;
    private String profileImage;
}
