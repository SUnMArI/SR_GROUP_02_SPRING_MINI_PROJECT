package com.example.springminiproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    private String fileName;
    private String fileUrl;
    private String fileType;
    private Long fileSize;
}
