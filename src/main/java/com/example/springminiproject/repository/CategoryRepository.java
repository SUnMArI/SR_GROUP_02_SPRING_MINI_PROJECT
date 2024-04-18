package com.example.springminiproject.repository;

import com.example.springminiproject.model.Category;
import com.example.springminiproject.model.dto.request.CategoryRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryRepository {
    @Results(id = "ResultMapper", value = {
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "user",column = "user_id",one = @One(select = "com.example.springminiproject.repository.AuthRepository.getUserById"))
    })
    @Select("""
    SELECT * FROM Categories;
    """)
    List<Category> getAllCategory();

     @Select("""
        SELECT * FROM Categories WHERE category_id =#{categoryId}
      """)
     @ResultMap("ResultMapper")
     Category getCategoryById(Integer id);

     @Select("""
        INSERT INTO Categories VALUES (default,#{category.name},#{category.description},11) RETURNING *
     """)
     @ResultMap("ResultMapper")
    Category insertCategory(@Param("category") CategoryRequest categoryRequest);
}
