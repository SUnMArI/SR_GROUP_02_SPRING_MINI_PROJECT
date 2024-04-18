package com.example.springminiproject.service.serviceImplement;

import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.model.Category;
import com.example.springminiproject.model.request.CategoryRequest;
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
    public List<Category> getAllCategory(Integer offset,Integer limit ) {
        offset=(offset-1)*limit;
        return categoryRepository.getAllCategory(offset,limit);
    }
    @Override
    public Category getcategoryById(Integer id) {
        int userId=2;
        Category category =categoryRepository.getCategoryById(id,userId);
        if (category==null){
            throw new NotFoundException("The category id "+id+" has not been founded.");
        }
        return category;
    }
    @Override
    public Category insertCategory(CategoryRequest categoryRequest) {
        return categoryRepository.insertCategory(categoryRequest,2);
    }
}
