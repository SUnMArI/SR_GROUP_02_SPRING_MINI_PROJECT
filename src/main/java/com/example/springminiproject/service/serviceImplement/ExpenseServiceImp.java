package com.example.springminiproject.service.serviceImplement;

import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.model.Category;
import com.example.springminiproject.model.Expense;
import com.example.springminiproject.model.User;
import com.example.springminiproject.model.dto.request.ExpenseRequest;
import com.example.springminiproject.repository.AuthRepository;
import com.example.springminiproject.repository.CategoryRepository;
import com.example.springminiproject.repository.ExpenseRepository;
import com.example.springminiproject.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseServiceImp implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final AuthRepository authRepository;
    private final CategoryRepository categoryRepository;
    String getUsernameOfCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
         return userDetails.getUsername();
    }

    @Override
    public List<Expense> getAllExpense(int offset, int limit, String column, String select) {
        String current_user = getUsernameOfCurrentUser();
        User user=authRepository.getUserByEmail(current_user);
        offset = (offset - 1) * limit;
        getUsernameOfCurrentUser();
        return expenseRepository.getAllExpense(offset, limit, column, select,user.getUserId());
    }

    @Override
    public Expense insertExpense(ExpenseRequest expenseRequest) {
        String current_user = getUsernameOfCurrentUser();
        User user=authRepository.getUserByEmail(current_user);
        Category category = categoryRepository.getCategoryById(expenseRequest.getCategoryId());
        if(category==null){
            throw new NotFoundException("Category ID "+expenseRequest.getCategoryId()+" Not found !!!");
        }
        return expenseRepository.insertExpense(expenseRequest, user.getUserId());
    }

    @Override
    public Expense getExpenseById(Integer id) {
        String current_user = getUsernameOfCurrentUser();
        User user=authRepository.getUserByEmail(current_user);
        Expense expense = expenseRepository.getExpenseById(id,user.getUserId());
        if(expense==null){
            throw new NotFoundException("Expense ID "+id+" Not found !!!");
        }
        return expense;
    }

    @Override
    public Expense updateExpense(Integer id, ExpenseRequest expenseRequest) {
        String current_user = getUsernameOfCurrentUser();
        User user=authRepository.getUserByEmail(current_user);
        Expense expense = expenseRepository.getExpenseById(id, user.getUserId());
        if(expense==null){
            throw new NotFoundException("Expense ID "+id+" Not found !!!");
        }
        Category category = categoryRepository.getCategoryById(expenseRequest.getCategoryId());
        if(category==null){
            throw new NotFoundException("Category ID "+expenseRequest.getCategoryId()+" Not found !!!");
        }
        expenseRepository.updateExpense(id, expenseRequest,user.getUserId());
        return expense;
    }

    @Override
    public Boolean deleteExpense(Integer id) {
        Boolean expense = expenseRepository.deleteExpense(id);
        if(!expense){
            throw new NotFoundException("Expense ID "+id+" Not found !!!");
        }
        return null;
    }
}
