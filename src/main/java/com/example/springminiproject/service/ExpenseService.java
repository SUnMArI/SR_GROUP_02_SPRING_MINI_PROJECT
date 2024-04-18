package com.example.springminiproject.service;

import com.example.springminiproject.model.Expense;
import com.example.springminiproject.model.dto.request.ExpenseRequest;

import java.util.List;

public interface ExpenseService {
    List<Expense> getAllExpense(int offset, int limit, String column, String select);

    Expense insertExpense(ExpenseRequest expenseRequest);

    Expense getExpenseById(Integer id);

    Expense updateExpense(Integer id, ExpenseRequest expenseRequest);

    Boolean deleteExpense(Integer id);
}
