package com.gestone.gestone_api.domain.bill;

import com.gestone.gestone_api.domain.expense.ExpenseResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record BillResponseDTO(
        UUID id,
        String name,
        String description,
        BigDecimal totalValue,
        BigDecimal paidValue,
        LocalDateTime dueDate,
        LocalDateTime paymentDate,
        BillCategory category,
        BillStatus status,
        String notes,
        LocalDateTime createdAt,
        List<ExpenseResponseDTO> expenses
) {
    public BillResponseDTO(Bill bill) {
        this(
                bill.getId(),
                bill.getName(),
                bill.getDescription(),
                bill.getTotalValue(),
                bill.getPaidValue(),
                bill.getDueDate(),
                bill.getPaymentDate(),
                bill.getCategory(),
                bill.getStatus(),
                bill.getNotes(),
                bill.getCreatedAt(),
                bill.getExpenses().stream().map(ExpenseResponseDTO::new).toList()
        );
    }
}
