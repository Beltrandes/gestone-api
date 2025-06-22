package com.gestone.gestone_api.domain.bill;

import com.gestone.gestone_api.domain.expense.ExpenseRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record BillRequestDTO(
        String name,
        String description,
        BigDecimal totalValue,
        LocalDateTime dueDate,
        BillCategory category,
        String notes,
        List<ExpenseRequestDTO> expenses
) {
}
