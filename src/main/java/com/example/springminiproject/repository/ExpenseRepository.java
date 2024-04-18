package com.example.springminiproject.repository;

import com.example.springminiproject.model.Expense;
import com.example.springminiproject.model.dto.request.ExpenseRequest;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ExpenseRepository {
    @Results(id = "expenseMapper", value = {
            @Result(property = "expenseId", column = "expense_id"),
            @Result(property = "user", column = "user_id",one = @One(select = "com.example.springminiproject.repository.AuthRepository.getUserById")),
            @Result(property = "category", column = "category_id",one = @One(select = "com.example.springminiproject.repository.CategoryRepository.getCategoryById"))
    })
    @Select("""
        SELECT * FROM expenses WHERE user_id = #{userId} ORDER BY ${column} ${select} LIMIT #{limit} OFFSET #{offset}
""")
    List<Expense> getAllExpense(int offset, int limit, String column, String select,Integer userId);

    @Select("""
        INSERT INTO expenses VALUES (
    default, #{expense.amount},#{expense.description}, #{expense.date},#{userId}, #{expense.categoryId}) RETURNING *
""")
    @ResultMap("expenseMapper")
    Expense insertExpense(@Param("expense") ExpenseRequest expenseRequest,Integer userId);

    @Select("""
        SELECT * FROM expenses WHERE expense_id = #{id} AND user_id = #{userId}
    """)
    @ResultMap("expenseMapper")
    Expense getExpenseById(Integer id,Integer userId);

    @Select("""
        UPDATE expenses SET amount = #{expense.amount}, description = #{expense.description},date = #{expense.date},user_id = #{userId},category_id = #{expense.categoryId} WHERE expense_id  = #{id} RETURNING *
    """)
    @ResultMap("expenseMapper")
    Expense updateExpense(Integer id,@Param("expense") ExpenseRequest expenseRequest,Integer userId);

    @Delete("""
        DELETE FROM expenses WHERE expense_id = #{id}
    """)
    Boolean deleteExpense(Integer id);
}
