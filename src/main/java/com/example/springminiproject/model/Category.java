package com.example.springminiproject.model;

import com.example.springminiproject.model.dto.response.AppUserResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Integer categoryId;
    private String name;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AppUserResponse user;
}
