package com.gestone.gestone_api.domain.payment;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentDTO(
        String details,
        BigDecimal payedValue,
        UUID marbleshopOrderId,
        PaymentType paymentType
) {
}
