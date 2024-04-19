package com.example.springminiproject.model.request;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.UUID;

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
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID categoryId;
}
