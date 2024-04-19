package com.example.springminiproject.model;

import com.example.springminiproject.model.dto.response.AppUserResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
//    private Integer categoryId;
@Id
@GeneratedValue(generator = "uuid2")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID categoryId;
    private String name;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AppUserResponse user;
}
