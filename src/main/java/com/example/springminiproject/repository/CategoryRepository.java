package com.example.springminiproject.repository;

import com.example.springminiproject.exception.UUIDTypeHandler;
import com.example.springminiproject.model.Category;
import com.example.springminiproject.model.dto.request.CategoryRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface CategoryRepository {
    @Results(id = "ResultMapper", value = {
            @Result(property = "categoryId", column = "category_id",javaType = UUID.class,typeHandler = UUIDTypeHandler.class),
            @Result(property = "user",column = "user_id",one = @One(select = "com.example.springminiproject.repository.AuthRepository.getUserById"),javaType = UUID.class,typeHandler = UUIDTypeHandler.class)
    })
    @Select("""
        SELECT * FROM Categories WHERE user_id = CAST(#{userId} AS UUID) LIMIT #{limit} OFFSET #{offset}
    """)
    List<Category> getAllCategory(Integer offset,Integer limit,UUID userId);

     @Select("""
        SELECT * FROM Categories WHERE category_id = CAST(#{categoryId} AS UUID) AND user_id = CAST(#{userId} AS UUID)
      """)
     @ResultMap("ResultMapper")
     Category getCategoryById(UUID id,UUID userId);

    @Select("""
        SELECT category_id,name,description FROM Categories WHERE category_id = CAST(#{categoryId} AS UUID)
    """)
    @ResultMap("ResultMapper")
    Category getCategoryByCateId(UUID id);

     @Select("""
        INSERT INTO Categories VALUES (default,#{category.name},#{category.description},CAST(#{userId} AS UUID)) RETURNING *
     """)
     @ResultMap("ResultMapper")
    Category insertCategory(@Param("category") CategoryRequest categoryRequest,UUID userId);

     @Select("""
        UPDATE Categories SET name = #{category.name}, description = #{category.description},user_id = CAST(#{userId} AS UUID) WHERE category_id = CAST(#{id} AS UUID) RETURNING *      
    """)
     @ResultMap("ResultMapper")
    Category updateCategory(UUID id,@Param("category") CategoryRequest categoryRequest,UUID userId);

     @Delete("""
        DELETE FROM categories WHERE category_id = CAST(#{id} AS UUID)
    """)
    Boolean deleteCategory(UUID id);
}
