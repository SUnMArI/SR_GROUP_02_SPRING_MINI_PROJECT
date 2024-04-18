package com.example.springminiproject.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest {
    @NotNull
    @Positive
    private Double amount;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Positive
    private Integer categoryId;
}
