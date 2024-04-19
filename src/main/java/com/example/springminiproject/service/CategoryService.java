package com.example.springminiproject.service;

import com.example.springminiproject.model.dto.Category;
import com.example.springminiproject.model.request.CategoryRequest;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAllCategory(Integer offset,Integer limit);

    Category getCategoryById(UUID id);

    Category insertCategory(CategoryRequest categoryRequest);

    Category updateCategory(UUID id, CategoryRequest categoryRequest);

    Boolean deleteCategory(UUID id);
}
