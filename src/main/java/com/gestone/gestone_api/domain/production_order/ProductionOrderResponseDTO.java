package com.gestone.gestone_api.domain.production_order;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ProductionOrderResponseDTO(
        UUID id,
        UUID orderId,
        LocalDate expectedStartDate,
        LocalDate realStartDate,
        LocalDate expectedEndDate,
        LocalDate realEndDate,
        ProductionStatus productionStatus,
        ProductionPriority productionPriority,
        String notes,
        String projectUrl,
        List<ProductionOrderItemResponseDTO> productionOrderItems

) {
    public ProductionOrderResponseDTO(ProductionOrder productionOrder) {
        this(
                productionOrder.getId(),
                productionOrder.getOrder().getId(),
                productionOrder.getExpectedStartDate(),
                productionOrder.getRealStartDate(),
                productionOrder.getExpectedEndDate(),
                productionOrder.getRealEndDate(),
                productionOrder.getProductionStatus(),
                productionOrder.getProductionPriority(),
                productionOrder.getNotes(),
                productionOrder.getProjectUrl(),
                productionOrder.getProductionOrderItems().stream().map(ProductionOrderItemResponseDTO::new).toList()
        );
    }
}
