package com.gestone.gestone_api.domain.slab;

import com.gestone.gestone_api.domain.marbleshop_material.MarbleshopMaterialResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record SlabResponseDTO(
        UUID id,
        MarbleshopMaterialResponseDTO material,
        BigDecimal measureX,
        BigDecimal measureY,
        SlabQuality quality,
        BigDecimal densityMeasure,
        String notes,
        SlabStatus status,
        LocalDate entryDate,
        LocalDateTime createdAt
) {
    public SlabResponseDTO(Slab slab) {
        this(
             slab.getId(),
             new MarbleshopMaterialResponseDTO(slab.getMaterial()),
             slab.getMeasureX(),
             slab.getMeasureY(),
             slab.getQuality(),
             slab.getDensityMeasure(),
             slab.getNotes(),
             slab.getStatus(),
             slab.getEntryDate(),
             slab.getCreatedAt()
        );
    }
}
