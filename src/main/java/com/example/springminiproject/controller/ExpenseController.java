package com.example.springminiproject.controller;

import com.example.springminiproject.model.dto.Expense;
import com.example.springminiproject.model.request.ExpenseRequest;
import com.example.springminiproject.model.response.ApiResponse;
import com.example.springminiproject.service.ExpenseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/expense")
@AllArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Expense>>> getAllExpense(
            @RequestParam(defaultValue = "1") @Positive int offset,
            @RequestParam(defaultValue = "3") @Positive int limit,
            @RequestParam String column,
            @Parameter(description = "Default value false", schema = @Schema(allowableValues = {"FALSE","TRUE"}))
            @RequestParam(name = "select") Boolean select){
        ApiResponse<List<Expense>> response = null;
        if(select){
            response = ApiResponse.<List<Expense>>builder()
                    .message("Show all expense list.")
                    .payload(expenseService.getAllExpense(offset, limit, column, "DESC"))
                    .httpStatus(HttpStatus.OK)
                    .dateFormat(LocalDateTime.now())
                    .build();
        }else if(!select){
            response = ApiResponse.<List<Expense>>builder()
                    .message("Show all expense list.")
                    .payload(expenseService.getAllExpense(offset, limit, column, "ASC"))
                    .httpStatus(HttpStatus.OK)
                    .dateFormat(LocalDateTime.now())
                    .build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("")
    public ResponseEntity<ApiResponse<Expense>> insertExpense(@RequestBody @Valid ExpenseRequest expenseRequest){
        ApiResponse<Expense> response = ApiResponse.<Expense>builder()
                .message("Insert expense successfully.")
                .payload(expenseService.insertExpense(expenseRequest))
                .httpStatus(HttpStatus.OK)
                .dateFormat(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Expense>> getExpenseById(@PathVariable UUID id){
        ApiResponse<Expense> response = ApiResponse.<Expense>builder()
                .message("Show detail expense "+id+" by id")
                .payload(expenseService.getExpenseById(id))
                .httpStatus(HttpStatus.OK)
                .dateFormat(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<Expense>> updateExpense(@PathVariable @Positive UUID id,@RequestBody @Valid ExpenseRequest expenseRequest){
        ApiResponse<Expense> response = ApiResponse.<Expense>builder()
                .message("Update expense "+id+" successfully")
                .payload(expenseService.updateExpense(id, expenseRequest))
                .httpStatus(HttpStatus.OK)
                .dateFormat(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<?>> deleteExpense(@PathVariable @Positive UUID id){
        ApiResponse<?> response = ApiResponse.builder()
                .message("Delete expense "+id+" successfully.")
                .payload(expenseService.deleteExpense(id))
                .httpStatus(HttpStatus.OK)
                .dateFormat(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
