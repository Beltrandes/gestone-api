package com.gestone.gestone_api.domain.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentResponseDTO(
        UUID id,
        String customerName,
        String details,
        BigDecimal totalValue,
        BigDecimal payedValue,
        BigDecimal balanceValue,
        PaymentType paymentType,
        UUID marbleshopOrderId,
        LocalDateTime createdAt
) {
    public PaymentResponseDTO(Payment payment) {
        this(
                payment.getId(),
                payment.getCustomer().getName(),
                payment.getDetails(),
                payment.getTotalValue(),
                payment.getPayedValue(),
                payment.getBalanceValue(),
                payment.getPaymentType(),
                payment.getMarbleshopOrder().getId(),
                payment.getCreatedAt()
        );
    }
}

