package com.gestone.gestone_api.domain.material;

import java.math.BigDecimal;

public record MaterialDTO(
    String name,
    String details,
    BigDecimal price,
    MaterialType materialType
) {
}
