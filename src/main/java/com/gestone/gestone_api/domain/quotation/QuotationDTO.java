package com.gestone.gestone_api.domain.quotation;

import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopItemDTO;
import com.gestone.gestone_api.domain.miscellaneous_item.MiscellaneousItemDTO;

import java.util.List;
import java.util.UUID;

public record QuotationDTO(
        String name,
        String details,
        String address,
        Integer deadlineDays,
        Integer daysForDue,
        UUID customerId,
        String userEmail,
        List<MarbleshopItemDTO> marbleshopItems,
        List<MiscellaneousItemDTO> miscellaneousItems
) {
}
