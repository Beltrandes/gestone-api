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
        String supplierName,
        BigDecimal totalValue,
        BigDecimal paidValue,
        LocalDateTime dueDate,
        LocalDateTime paymentDate,
        BillCategory category,
        BillStatus status,
        String notes,
        Boolean isRecurring,
        Integer installmentNumber,
        Integer totalInstallments,
        UUID parentBillId,
        LocalDateTime createdAt,
        List<ExpenseResponseDTO> expenses
) {
    public BillResponseDTO(Bill bill) {
        this(
                bill.getId(),
                bill.getName(),
                bill.getDescription(),
                bill.getSupplierName(),
                bill.getTotalValue(),
                bill.getPaidValue(),
                bill.getDueDate(),
                bill.getPaymentDate(),
                bill.getCategory(),
                bill.getStatus(),
                bill.getNotes(),
                bill.getIsRecurring(),
                bill.getInstallmentNumber(),
                bill.getTotalInstallments(),
                bill.getParentBillId(),
                bill.getCreatedAt(),
                bill.getExpenses() != null ? bill.getExpenses().stream().map(ExpenseResponseDTO::new).toList() : List.of()
        );
    }
}
