package com.gestone.gestone_api.domain.slab;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record SlabRequestDTO(
        UUID materialId,
        BigDecimal measureX,
        BigDecimal measureY,
        SlabQuality quality,
        BigDecimal densityMeasure,
        String notes,
        SlabStatus status,
        LocalDate entryDate
) {
}
