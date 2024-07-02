package com.gestone.gestone_api.domain.quotation;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
public record MarbleshopItemDTO(
    String name,
    String description,
    BigDecimal measureX,
    BigDecimal measureY,
    Integer quantity,
    UUID marbleshopMaterialId,
    List<MarbleshopSubItemDTO> marbleshopSubItems
) {

}
