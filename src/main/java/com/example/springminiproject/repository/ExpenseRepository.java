package com.example.springminiproject.repository;

import com.example.springminiproject.exception.UUIDTypeHandler;
import com.example.springminiproject.model.Expense;
import com.example.springminiproject.model.dto.request.ExpenseRequest;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Mapper
public interface ExpenseRepository {
    @Results(id = "expenseMapper", value = {
            @Result(property = "amount",column = "amount"),
            @Result(property = "expenseId", column = "expense_id",javaType = UUID.class,typeHandler = UUIDTypeHandler.class),
            @Result(property = "user", column = "user_id",one = @One(select = "com.example.springminiproject.repository.AuthRepository.getUserById"),javaType = UUID.class,typeHandler = UUIDTypeHandler.class),
            @Result(property = "category", column = "category_id",one = @One(select = "com.example.springminiproject.repository.CategoryRepository.getCategoryByCateId"),javaType = UUID.class,typeHandler = UUIDTypeHandler.class)
    })
    @Select("""
        SELECT * FROM expenses WHERE user_id = CAST(#{userId} AS UUID) ORDER BY ${column} ${select} LIMIT #{limit} OFFSET #{offset}
""")
    List<Expense> getAllExpense(int offset, int limit, String column, String select, UUID userId);

    @Select("""
        INSERT INTO expenses VALUES (
    default, #{expense.amount},#{expense.description}, #{expense.date},CAST(#{userId} AS UUID), CAST(#{expense.categoryId} AS UUID)) RETURNING *
""")
    @ResultMap("expenseMapper")
    Expense insertExpense(@Param("expense") ExpenseRequest expenseRequest,UUID userId);

    @Select("""
        SELECT * FROM expenses WHERE expense_id = CAST(#{id} AS UUID) AND user_id = CAST(#{userId} AS UUID)
    """)
    @ResultMap("expenseMapper")
    Expense getExpenseById(UUID id,UUID userId);

    @Select("""
        UPDATE expenses SET amount = #{expense.amount}, description = #{expense.description},date = #{expense.date},user_id = CAST(#{userId} AS UUID),category_id = CAST(#{expense.categoryId} AS UUID) WHERE expense_id  = CAST(#{id} AS UUID) RETURNING *
    """)
    @ResultMap("expenseMapper")
    Expense updateExpense(UUID id,@Param("expense") ExpenseRequest expenseRequest,UUID userId);

    @Delete("""
        DELETE FROM expenses WHERE expense_id = CAST(#{id} AS UUID)
    """)
    Boolean deleteExpense(UUID id);
}
