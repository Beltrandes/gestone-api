package com.gestone.gestone_api.domain.supply;

import java.time.LocalDateTime;
import java.util.UUID;

public record SupplyMovementResponseDTO(
        UUID id,
        UUID supplyId,
        SupplyMovementType type,
        SupplyMovementCategory category,
        Integer quantity,
        String notes,
        String userName,
        LocalDateTime createdAt
) {
    public SupplyMovementResponseDTO(SupplyMovement movement) {
        this(
                movement.getId(),
                movement.getSupply().getId(),
                movement.getType(),
                movement.getCategory(),
                movement.getQuantity(),
                movement.getNotes(),
                movement.getUserAuth().getName(),
                movement.getCreatedAt()
        );
    }
}
