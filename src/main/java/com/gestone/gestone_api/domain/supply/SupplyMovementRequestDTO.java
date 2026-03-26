package com.gestone.gestone_api.domain.supply;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SupplyMovementRequestDTO(
        @NotNull SupplyMovementType type,
        @NotNull SupplyMovementCategory category,
        @NotNull @Min(1) Integer quantity,
        String notes
) {
}
