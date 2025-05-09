package com.gestone.gestone_api.domain.marbleshop_item;

import java.math.BigDecimal;
import java.util.UUID;

public record MarbleshopSubItemDTO(
        UUID id,
        String name,
        String description,
        BigDecimal measureX,
        BigDecimal measureY,
        Integer quantity,
        MarbleshopSubItemType marbleshopSubItemType,
        UUID marbleshopItemId
) {

}
