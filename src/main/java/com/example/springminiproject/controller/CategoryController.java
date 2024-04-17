package com.example.springminiproject.controller;

import com.example.springminiproject.model.Category;
import com.example.springminiproject.model.request.CategoryRequest;
import com.example.springminiproject.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
//    Get all
    @GetMapping
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }
//    Get By id
    @GetMapping("/{id}")
    public Category getcategoryById(@PathVariable Integer id){

        return categoryService.getcategoryById(id);
    }
//    Insert category
    @PostMapping
    public Category insertCategory(@RequestBody CategoryRequest categoryRequest){

        return categoryService.insertCategory(categoryRequest);
    }
}
