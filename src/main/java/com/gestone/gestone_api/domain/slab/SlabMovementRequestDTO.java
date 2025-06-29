package com.gestone.gestone_api.domain.slab;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record SlabMovementRequestDTO(
        UUID productionOrderItemId,
        UUID slabId,
        BigDecimal areaMoved,
        SlabMovementType movementType
) {


}
