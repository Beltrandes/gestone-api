package com.gestone.gestone_api.domain.payment;

import com.gestone.gestone_api.domain.customer.CustomerResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentResponseDTO(
        UUID id,
        CustomerResponseDTO customer,
        String details,
        BigDecimal payedValue,
        PaymentType paymentType,
        UUID marbleshopOrderId,
        BigDecimal orderFinalValue,
        PaymentStatus orderPaymentStatus,
        LocalDateTime paymentDate,
        LocalDateTime createdAt
) {
    public PaymentResponseDTO(Payment payment) {
        this(
                payment.getId(),
                new CustomerResponseDTO(payment.getCustomer()),
                payment.getDetails(),
                payment.getPayedValue(),
                payment.getPaymentType(),
                payment.getMarbleshopOrder().getId(),
                payment.getMarbleshopOrder().getFinalValue(),
                payment.getMarbleshopOrder().calculatePaymentStatus(),
                payment.getPaymentDate(),
                payment.getCreatedAt()
        );
    }
}

