package com.example.springminiproject.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppFileResponse {
    private String fileName;
    private String fileUrl;
    private String fileType;
    private long fileSize;
}
