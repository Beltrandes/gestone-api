package com.gestone.gestone_api.domain.expense;

import com.gestone.gestone_api.domain.bill.BillCategory;
import com.gestone.gestone_api.domain.bill.BillStatus;
import com.gestone.gestone_api.domain.payment.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ExpenseResponseDTO(
        UUID id,
        String details,
        BigDecimal paidValue,
        PaymentType paymentType,
        LocalDateTime paymentDate,
        LocalDateTime createdAt,
        String billName,
        BigDecimal billTotalValue,
        BillStatus billStatus,
        BillCategory billCategory,
        String billDescription,
        UUID billId
) {
    public ExpenseResponseDTO(Expense expense) {
        this(
                expense.getId(),
                expense.getDetails(),
                expense.getPaidValue(),
                expense.getPaymentType(),
                expense.getPaymentDate(),
                expense.getCreatedAt(),
                expense.getBill().getName(),
                expense.getBill().getTotalValue(),
                expense.getBill().getStatus(),
                expense.getBill().getCategory(),
                expense.getBill().getDescription(),
                expense.getBill().getId()
        );
    }
}
