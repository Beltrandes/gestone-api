package com.gestone.gestone_api.domain.production_order;

import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopItemResponseDTO;
import com.gestone.gestone_api.domain.slab.SlabMovementResponseDTO;

import java.util.List;
import java.util.UUID;

public record ProductionOrderItemResponseDTO(
        UUID id,
        UUID productionOrderId,
        MarbleshopItemResponseDTO marbleshopItem,
        List<SlabMovementResponseDTO> slabMovements,
        ProductionOrderItemStatus status,
        String notes
) {

    public ProductionOrderItemResponseDTO(ProductionOrderItem productionOrderItem) {
        this(
                productionOrderItem.getId(),
                productionOrderItem.getProductionOrder().getId(),
                new MarbleshopItemResponseDTO(productionOrderItem.getMarbleshopItem()),
                productionOrderItem.getSlabMovements().stream().map(SlabMovementResponseDTO::new).toList(),
                productionOrderItem.getStatus(),
                productionOrderItem.getNotes()

        );
    }
}
