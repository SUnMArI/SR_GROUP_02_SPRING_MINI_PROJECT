package com.example.springminiproject.controller;

import com.example.springminiproject.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/expense")
public class ExpenseController {
    private final ExpenseService expenseService;
}
