package com.gestone.gestone_api.domain.order;

import com.gestone.gestone_api.domain.payment.Payment;
import com.gestone.gestone_api.domain.payment.PaymentDTO;
import com.gestone.gestone_api.domain.payment.PaymentStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record MarbleshopOrderDTO(
    String workAddress,
    UUID quotationId,
    BigDecimal discount,
    List<PaymentDTO> payments,
    String notes
) {
}
