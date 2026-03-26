package com.gestone.gestone_api.domain.supply;

public record SupplyRequestDTO(
        String name,
        String description,
        String unitOfMeasure,
        Integer minimumStock
) {
}
