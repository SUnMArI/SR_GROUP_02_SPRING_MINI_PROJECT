package com.example.springminiproject.controller;

import com.example.springminiproject.model.Category;
import com.example.springminiproject.model.dto.response.ApiResponse;
import com.example.springminiproject.model.request.CategoryRequest;
import com.example.springminiproject.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
//    Get all
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllCategory(
            @RequestParam (defaultValue = "1" )Integer offset,
            @RequestParam (defaultValue = "5")Integer limit){
        ApiResponse<?> categoryResponse =ApiResponse.builder()
                .message("All categories have been successfully founded.")
                .payload(categoryService.getAllCategory(offset,limit))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }
//    Get By id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> getcategoryById(@PathVariable Integer id){
        ApiResponse<Category> categoryResponse =ApiResponse.<Category>builder()
                .message("The category has been successfully founded.")
                .payload(categoryService.getcategoryById(id))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }
//    Insert category
    @PostMapping
    public ResponseEntity<ApiResponse<Category>> insertCategory(@RequestBody @Valid CategoryRequest categoryRequest){
        ApiResponse<Category> categoryResponse =ApiResponse.<Category>builder()
                .message("The category has been successfully created.")
                .payload(categoryService.insertCategory(categoryRequest))
                .status(HttpStatus.CREATED)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }
}
