package com.gestone.gestone_api.domain.slab;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record SlabMovementResponseDTO(
        UUID id,
        UUID slabId,
        SlabMovementType movementType,
        UUID productionOrderItemId,
        BigDecimal areaMoved,
        String notes,
        LocalDateTime createdAt
) {

    public SlabMovementResponseDTO(SlabMovement slabMovement) {
        this(
                slabMovement.getId(),
                slabMovement.getSlab().getId(),
                slabMovement.getMovementType(),
                slabMovement.getProductionOrderItem() != null ? slabMovement.getProductionOrderItem().getId() : null,
                slabMovement.getAreaMoved(),
                slabMovement.getNotes(),
                slabMovement.getCreatedAt()
        );
    }
}
