package com.gestone.gestone_api.domain.material;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateMaterialPriceDTO(
    UUID materialId,
    BigDecimal price
) {
}
