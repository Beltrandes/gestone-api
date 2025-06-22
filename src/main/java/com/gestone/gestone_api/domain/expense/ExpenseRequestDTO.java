package com.gestone.gestone_api.domain.expense;

import com.gestone.gestone_api.domain.payment.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ExpenseRequestDTO(
        BigDecimal paidValue,
        String details,
        LocalDateTime paymentDate,
        PaymentType paymentType,
        UUID billId

) {
}
