package com.example.springminiproject.repository;

import com.example.springminiproject.model.dto.Category;
import com.example.springminiproject.model.request.CategoryRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryRepository {
    @Results(id = "ResultMapper", value = {
            @Result(property = "categoryId", column = "category_id")
    })
//    Get ALL
    @Select("""
    SELECT * FROM Categories;
    """)
    List<Category> getAllCategory();

//    Get by id
     @Select("""
        SELECT * FROM Categories WHERE category_id =#{categoryId}
       """)
     @ResultMap("ResultMapper")
    Category getcategoryById(Integer id);

//     Insert Category
     @Select("""
     INSERT INTO Categories()
    """)
    Category insertCategory(@Param("category") CategoryRequest categoryRequest);
}
