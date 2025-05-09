package com.gestone.gestone_api.domain.marbleshop_item;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record MarbleshopItemDTO(UUID id, String name, String description, BigDecimal measureX, BigDecimal measureY,
                                Integer quantity, UUID marbleshopMaterialId, UUID quotationId,
                                List<MarbleshopSubItemDTO> marbleshopSubItems, MarbleshopItemType marbleshopItemType) {

}
