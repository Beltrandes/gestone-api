package com.gestone.gestone_api.domain.marbleshop_item;

import java.math.BigDecimal;
import java.util.UUID;

public record MarbleshopSubItemDTO(
        String name,
        String description,
        BigDecimal measureX,
        BigDecimal measureY,
        Integer quantity,
        UUID marbleshopMaterialId,
        MarbleshopSubItemType marbleshopSubItemType,
        UUID marbleshopItemId
) {

}
