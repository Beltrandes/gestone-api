package com.gestone.gestone_api.domain.material;

import java.math.BigDecimal;

public record MarbleshopMaterialDTO(
    String name,
    String details,
    BigDecimal price,
    MarbleshopMaterialType marbleshopMaterialType
) {
}
