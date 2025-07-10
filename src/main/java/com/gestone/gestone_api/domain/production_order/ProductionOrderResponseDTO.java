package com.gestone.gestone_api.domain.production_order;

import com.gestone.gestone_api.domain.customer.CustomerResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ProductionOrderResponseDTO(
        UUID id,
        UUID orderId,
        CustomerResponseDTO customer,
        LocalDate expectedStartDate,
        LocalDate realStartDate,
        LocalDate expectedEndDate,
        LocalDate realEndDate,
        LocalDateTime expectedInstallmentDate,
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
                new CustomerResponseDTO(productionOrder.getOrder().getCustomer()),
                productionOrder.getExpectedStartDate(),
                productionOrder.getRealStartDate(),
                productionOrder.getExpectedEndDate(),
                productionOrder.getRealEndDate(),
                productionOrder.getOrder().getEstimatedInstallmentDate(),
                productionOrder.getProductionStatus(),
                productionOrder.getProductionPriority(),
                productionOrder.getNotes(),
                productionOrder.getProjectUrl(),
                productionOrder.getProductionOrderItems().stream().map(ProductionOrderItemResponseDTO::new).toList()
        );
    }
}
