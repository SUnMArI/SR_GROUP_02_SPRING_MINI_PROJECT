package com.example.springminiproject.service.serviceImplement;

import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.model.Category;
import com.example.springminiproject.model.User;
import com.example.springminiproject.model.dto.request.CategoryRequest;
import com.example.springminiproject.repository.AuthRepository;
import com.example.springminiproject.repository.CategoryRepository;
import com.example.springminiproject.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final AuthRepository authRepository;
    String getUsernameOfCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userDetails.getUsername();
    }

    @Override
    public List<Category> getAllCategory(Integer offset,Integer limit) {
        offset = (offset - 1) * limit;
        String current_user = getUsernameOfCurrentUser();
        User user=authRepository.getUserByEmail(current_user);
        return categoryRepository.getAllCategory(offset,limit,user.getUserId());
    }

    @Override
    public Category getCategoryById(UUID id) {
        String current_user = getUsernameOfCurrentUser();
        User user=authRepository.getUserByEmail(current_user);
        Category category = categoryRepository.getCategoryById(id,user.getUserId());
        if(category==null){
            throw new NotFoundException("Category ID "+id+" Not found");
        }
        return category;
    }

    @Override
    public Category insertCategory(CategoryRequest categoryRequest) {
        String current_user = getUsernameOfCurrentUser();
        User user=authRepository.getUserByEmail(current_user);
        return categoryRepository.insertCategory(categoryRequest,user.getUserId());
    }

    @Override
    public Category updateCategory(UUID id, CategoryRequest categoryRequest) {
        String current_user = getUsernameOfCurrentUser();
        User user=authRepository.getUserByEmail(current_user);
        Category category = categoryRepository.getCategoryById(id,user.getUserId());
        if(category==null){
            throw new NotFoundException("Category ID "+id+" Not found");
        }
        return categoryRepository.updateCategory(id,categoryRequest,user.getUserId());
    }

    @Override
    public Boolean deleteCategory(UUID id) {
        String current_user = getUsernameOfCurrentUser();
        User user=authRepository.getUserByEmail(current_user);
        Category category = categoryRepository.getCategoryById(id,user.getUserId());
        if(category==null){
            throw new NotFoundException("Category ID "+id+" Not found");
        }
        categoryRepository.deleteCategory(id);
        return null;
    }
}
