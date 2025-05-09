package com.gestone.gestone_api.domain.miscellaneous_material;

import java.math.BigDecimal;

public record MiscellaneousMaterialDTO(
        String name,
        String details,
        BigDecimal price,
        MiscellaneousMaterialType miscellaneousMaterialType
) {
}
