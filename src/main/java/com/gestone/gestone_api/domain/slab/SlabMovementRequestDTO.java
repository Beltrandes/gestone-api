package com.gestone.gestone_api.domain.slab;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record SlabMovementRequestDTO(
        UUID slabId,
        UUID productionOrderItemId, // Optional
        BigDecimal areaMoved,       // Optional (used for manual movements)
        SlabMovementType movementType,
        String notes
) {


}
