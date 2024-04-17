package com.example.springminiproject.service;

import com.example.springminiproject.model.Category;
import com.example.springminiproject.model.request.CategoryRequest;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();

    Category getcategoryById(Integer id);

    Category insertCategory(CategoryRequest categoryRequest);
}
