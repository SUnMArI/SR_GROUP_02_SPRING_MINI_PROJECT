package com.example.springminiproject.service.serviceImplement;

import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.model.Category;
import com.example.springminiproject.model.dto.request.CategoryRequest;
import com.example.springminiproject.model.dto.response.CategoryResponse;
import com.example.springminiproject.repository.CategoryRepository;
import com.example.springminiproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<CategoryResponse> getAllCategory(Integer offset, Integer limit){
        offset = (offset -1) * limit;
        List<Category> categories = categoryRepository.getAllCategory(offset,limit);
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for(Category category : categories){
            CategoryResponse categoryResponse = modelMapper.map(category, CategoryResponse.class);
            categoryResponses.add(categoryResponse);
        }
        return categoryResponses;
    }


    @Override
    public CategoryResponse updateCategoryById(Integer id, CategoryRequest categoryRequest){
        Category category = categoryRepository.updateCategoryById(id,categoryRequest);
        if(category == null){
            throw new NotFoundException("The Category id " + id + " has not been found");
        }
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public boolean removeCategoryById(Integer id){
        int userId =3;
        Category category = categoryRepository.findCategoryUser(id , userId);
//        if(category==null){
//            throw new NotFoundException("The CategoryUser " + userId + " has not been found");
//        }
        boolean issuccess = categoryRepository.removeCategoryById(id);
        if(!issuccess){
            throw new NotFoundException("The Category id " + id + " has not been found");
        }
            return issuccess;
    }

}
