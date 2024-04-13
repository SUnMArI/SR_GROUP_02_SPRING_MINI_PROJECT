package com.example.springminiproject.service.serviceImplement;

import com.example.springminiproject.repository.AppFileRepository;
import com.example.springminiproject.service.AppFileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppFileServiceImp implements AppFileService {
    private final AppFileRepository appFileRepository;
}
