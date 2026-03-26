package com.gestone.gestone_api.domain.supply;

import java.util.UUID;

public record SupplyResponseDTO(
        UUID id,
        String name,
        String description,
        String unitOfMeasure,
        Integer minimumStock,
        Integer currentStock,
        boolean isLowStock
) {
    public SupplyResponseDTO(Supply supply) {
        this(
                supply.getId(),
                supply.getName(),
                supply.getDescription(),
                supply.getUnitOfMeasure(),
                supply.getMinimumStock(),
                supply.getCurrentStock(),
                supply.getCurrentStock() <= supply.getMinimumStock()
        );
    }
}
