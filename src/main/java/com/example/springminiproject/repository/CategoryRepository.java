package com.example.springminiproject.repository;

import com.example.springminiproject.model.Category;
import com.example.springminiproject.model.dto.request.CategoryRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CategoryRepository {

    @Results(id = "CategoryMapper", value = {
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "categoryName", column = "name"),
            @Result(property = "categoryDescription", column = "description")

    })

    @Select("""
                SELECT  * FROM categories  LIMIT #{limit} OFFSET #{offset};
            """)
    List<Category> getAllCategory(Integer offset, Integer limit);


    // Update Category By ID

    @Select("""

            UPDATE categories SET name = #{category.categoryName}, description = #{category.categoryDescription} WHERE category_id = #{id}  RETURNING *
            """)
    @ResultMap("CategoryMapper")
    Category updateCategoryById(Integer id, @Param("category") CategoryRequest categoryRequest);

//     Delete
    @Select("""
            SELECT * FROM categories WHERE category_id = 5
            """)
    Category findCategoryUser(Integer id, Integer userId);

    @Delete("""
            DELETE FROM categories WHERE category_id = #{id};
            """)
    boolean removeCategoryById(Integer id);
}