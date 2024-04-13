package com.example.springminiproject.controller;

import com.example.springminiproject.service.AppFileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/files")
public class AppFileController {
  private final AppFileService appFileService;
}
