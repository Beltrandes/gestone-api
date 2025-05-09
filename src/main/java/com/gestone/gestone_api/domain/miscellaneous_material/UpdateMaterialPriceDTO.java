package com.gestone.gestone_api.domain.miscellaneous_material;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateMaterialPriceDTO(
    UUID materialId,
    BigDecimal price
) {
}
