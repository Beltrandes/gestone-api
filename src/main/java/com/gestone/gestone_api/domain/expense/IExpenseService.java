package com.gestone.gestone_api.domain.expense;

import java.util.List;
import java.util.UUID;

public interface IExpenseService {
    Expense save(ExpenseRequestDTO expenseRequestDTO);

    List<Expense> findExpensesByMarbleshopId(UUID marbleshopId);
}
