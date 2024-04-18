package com.example.springminiproject.service.serviceImplement;

import com.example.springminiproject.model.Category;
import com.example.springminiproject.model.dto.request.CategoryRequest;
import com.example.springminiproject.repository.CategoryRepository;
import com.example.springminiproject.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.getAllCategory();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository.getCategoryById(id);
    }

    @Override
    public Category insertCategory(CategoryRequest categoryRequest) {
        return categoryRepository.insertCategory(categoryRequest);
    }
}
