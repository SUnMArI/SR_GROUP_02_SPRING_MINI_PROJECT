package com.example.springminiproject.controller;

import com.example.springminiproject.model.apiResponse.APIResponse;
import com.example.springminiproject.model.dto.request.CategoryRequest;
import com.example.springminiproject.model.dto.response.CategoryResponse;
import com.example.springminiproject.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<APIResponse<List<CategoryResponse>>>
            getAllCategory(@RequestParam(defaultValue = "1") Integer offset,
                           @RequestParam(defaultValue = "5") Integer limit){
        return ResponseEntity.status(HttpStatus.OK).body(
                new APIResponse<>(
                        "Get all Category successfully",
                        categoryService.getAllCategory(offset,limit),
                        HttpStatus.OK,
                        LocalDateTime.now()
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<CategoryResponse>>
    updateCategoryById(@PathVariable Integer id, @RequestBody @Valid CategoryRequest categoryRequest){
        APIResponse<CategoryResponse> response = APIResponse.<CategoryResponse>builder()
                .message("The Category has been successfully updated.")
                .payload(categoryService.updateCategoryById(id, categoryRequest))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<?>>removeCategoryById(@PathVariable Integer id){
        APIResponse<?> response = APIResponse.builder()
                .message("The Category has been successfully deleted.")
                .payload(categoryService.removeCategoryById(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
