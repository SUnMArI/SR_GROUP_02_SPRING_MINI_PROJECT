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
        INSERT INTO Categories VALUES (default,#{category.name},#{category.description},#{userId}) RETURNING *
     """)
     @ResultMap("ResultMapper")
    Category insertCategory(@Param("category") CategoryRequest categoryRequest,Integer userId);

     @Select("""
        UPDATE Categories SET name = #{category.name}, description = #{category.description},user_id =  #{userId} WHERE category_id = #{id} RETURNING *      
    """)
     @ResultMap("ResultMapper")
    Category updateCategory(Integer id,@Param("category") CategoryRequest categoryRequest,Integer userId);

     @Delete("""
        DELETE FROM categories WHERE category_id = #{id}
    """)
    Boolean deleteCategory(Integer id);
}
