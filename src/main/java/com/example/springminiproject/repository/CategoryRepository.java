package com.example.springminiproject.repository;

import com.example.springminiproject.model.Category;
import com.example.springminiproject.model.request.CategoryRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryRepository {
    @Results(id = "ResultMapper", value = {
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "com.example.springminiproject.repository.AuthRepository.getUserByID"))
    })
//    Get ALL
    @Select("""
            SELECT * FROM Categories LIMIT #{limit} OFFSET #{offset};
            """)
    List<Category> getAllCategory(Integer offset, Integer limit);

    //    Get by id
    @Select("""
             SELECT * FROM Categories
             WHERE category_id =#{categoryId}
             AND user_id =#{userId};
            """)
    @ResultMap("ResultMapper")
    Category getCategoryById(Integer categoryId, int userId);

    //     Insert Category
    @Select("""
             INSERT INTO Categories(name,description,user_id)
             VALUES (#{category.name},#{category.description},#{userId}) RETURNING *
            """)
    @ResultMap("ResultMapper")
    Category insertCategory(
            @Param("category") CategoryRequest categoryRequest, int userId
    );
}
