package com.gestone.gestone_api.domain.production_order;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ProductionOrderRequestDTO(
        UUID marbleshopOrderId,
        LocalDate expectedStartDate,
        LocalDate expectedEndDate,
        ProductionPriority productionPriority,
        String notes
) {
}
