package com.gestone.gestone_api.domain.slab;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record SlabMovementResponseDTO(
        UUID id,
        UUID slabId,
        UUID productionOrderItemId,
        BigDecimal areaMoved,
        SlabMovementType movementType,
        LocalDateTime createdAt,
        String notes
) {

    public SlabMovementResponseDTO(SlabMovement slabMovement) {
        this(
                slabMovement.getId(),
                slabMovement.getSlab().getId(),
                slabMovement.getProductionOrderItem().getId(),
                slabMovement.getAreaMoved(),
                slabMovement.getMovementType(),
                slabMovement.getCreatedAt(),
                slabMovement.getNotes()
        );
    }
}
