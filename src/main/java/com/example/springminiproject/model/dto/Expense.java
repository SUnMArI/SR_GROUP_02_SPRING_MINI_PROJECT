package com.example.springminiproject.model.dto;

import com.example.springminiproject.model.response.AppUserResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID expenseId;
    //    private Integer expenseId;
    private Double amount;
    private String description;
    private LocalDateTime date;
    private Category category;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AppUserResponse user;
}
