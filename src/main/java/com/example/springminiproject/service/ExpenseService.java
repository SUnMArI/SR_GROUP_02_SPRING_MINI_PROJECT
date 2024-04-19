package com.example.springminiproject.service;

import com.example.springminiproject.model.Expense;
import com.example.springminiproject.model.dto.request.ExpenseRequest;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    List<Expense> getAllExpense(int offset, int limit, String column, String select);

    Expense insertExpense(ExpenseRequest expenseRequest);

    Expense getExpenseById(UUID id);

    Expense updateExpense(UUID id, ExpenseRequest expenseRequest);

    Boolean deleteExpense(UUID id);
}
