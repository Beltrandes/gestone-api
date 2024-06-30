package com.gestone.gestone_api.domain.quotation;

import java.math.BigDecimal;
import java.util.UUID;

public record QuoteItemDTO(
        String name,
        String details,
        BigDecimal measureX,
        BigDecimal measureY,
        Integer quantity,
        UUID materialId
) {
}
