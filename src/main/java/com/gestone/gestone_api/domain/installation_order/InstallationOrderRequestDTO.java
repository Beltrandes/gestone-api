package com.gestone.gestone_api.domain.installation_order;

import java.time.LocalDateTime;
import java.util.UUID;

public record InstallationOrderRequestDTO(
        UUID orderId,
        InstallationOrderStatus status,
        String installers,
        String address,
        String notes,
        LocalDateTime scheduledDate,
        LocalDateTime completionDate
) {
}
