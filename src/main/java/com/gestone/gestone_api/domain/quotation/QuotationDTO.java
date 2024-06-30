package com.gestone.gestone_api.domain.quotation;

import java.util.List;
import java.util.UUID;

public record QuotationDTO(
        String name,
        String details,
        String address,
        Integer deadlineDays,

        Integer daysForDue,
        UUID customerId,
        List<QuoteItemDTO> quoteItems
) {
}
