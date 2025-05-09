package com.gestone.gestone_api.domain.marbleshop_material;

import java.math.BigDecimal;
import java.util.UUID;

public record MarbleshopMaterialDTO(UUID id, String name, String details, BigDecimal price,
                                    MarbleshopMaterialType marbleshopMaterialType) {
}
