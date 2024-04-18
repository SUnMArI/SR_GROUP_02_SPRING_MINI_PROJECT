package com.example.springminiproject.service;

import com.example.springminiproject.model.Category;
import com.example.springminiproject.model.dto.request.CategoryRequest;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();

    Category getCategoryById(Integer id);

    Category insertCategory(CategoryRequest categoryRequest);

    Category updateCategory(Integer id, CategoryRequest categoryRequest);

    Boolean deleteCategory(Integer id);
}
