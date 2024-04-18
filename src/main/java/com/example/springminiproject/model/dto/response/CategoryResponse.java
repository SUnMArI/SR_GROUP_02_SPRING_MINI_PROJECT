package com.example.springminiproject.model.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    @NotNull
    @NotBlank
    private Integer categoryId;


    @NotNull
    @NotBlank
    private String categoryName;


    @NotBlank
    @NotNull
    private String categoryDescription;



}
