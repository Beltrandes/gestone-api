package com.gestone.gestone_api.domain.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> saveExpense(@RequestBody ExpenseRequestDTO expenseRequestDTO) {
        Expense expense = this.expenseService.save(expenseRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ExpenseResponseDTO(expense));
    }

    @GetMapping("/{marbleshopId}")
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpensesByMarbleshopId(@PathVariable UUID marbleshopId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.expenseService.findExpensesByMarbleshopId(marbleshopId).stream().map(ExpenseResponseDTO::new).toList());
    }
}
