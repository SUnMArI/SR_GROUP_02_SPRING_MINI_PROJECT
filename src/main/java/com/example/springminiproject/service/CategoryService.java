package com.example.springminiproject.service;

import com.example.springminiproject.model.dto.request.CategoryRequest;
import com.example.springminiproject.model.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategory(Integer offset, Integer limit);

    CategoryResponse updateCategoryById(Integer id, CategoryRequest categoryRequest);
    boolean removeCategoryById(Integer id);
}
