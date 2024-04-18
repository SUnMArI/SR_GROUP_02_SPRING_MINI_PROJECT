package com.example.springminiproject.model;

import com.example.springminiproject.model.dto.response.AppUserResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense{
    private Integer expenseId;
    private Double amount;
    private String description;
    private LocalDateTime date;
    private Category category;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AppUserResponse user;
}
