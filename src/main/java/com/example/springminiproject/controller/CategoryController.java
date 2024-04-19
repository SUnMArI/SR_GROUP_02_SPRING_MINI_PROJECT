package com.example.springminiproject.controller;

import com.example.springminiproject.model.Category;
import com.example.springminiproject.model.dto.request.CategoryRequest;
import com.example.springminiproject.model.dto.response.ApiResponse;
import com.example.springminiproject.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategory( @RequestParam(defaultValue = "1") @Positive int offset,
                                                                       @RequestParam(defaultValue = "3") @Positive int limit){
        ApiResponse<List<Category>> apiResponse = ApiResponse.<List<Category>>builder()
                .message("All Category has founded")
                .payload(categoryService.getAllCategory(offset,limit))
                .httpStatus(HttpStatus.OK)
                .dateFormat(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable UUID id){
        ApiResponse<Category> apiResponse = ApiResponse.<Category>builder()
                .message("Category ID "+id+" has founded")
                .payload(categoryService.getCategoryById(id))
                .httpStatus(HttpStatus.OK)
                .dateFormat(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Category>> insertCategory(@RequestBody @Valid CategoryRequest categoryRequest){
        ApiResponse<Category> apiResponse = ApiResponse.<Category>builder()
                .message("Category created successfully")
                .payload(categoryService.insertCategory(categoryRequest))
                .httpStatus(HttpStatus.OK)
                .dateFormat(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> updateCategory(@PathVariable UUID id,@RequestBody @Valid CategoryRequest categoryRequest){
        ApiResponse<Category> apiResponse = ApiResponse.<Category>builder()
                .message("Category ID "+id+" has updated successfully")
                .payload(categoryService.updateCategory(id,categoryRequest))
                .httpStatus(HttpStatus.OK)
                .dateFormat(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteCategory(@PathVariable UUID id){
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Category ID "+id+" has deleted successfully")
                .payload(categoryService.deleteCategory(id))
                .httpStatus(HttpStatus.OK)
                .dateFormat(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
