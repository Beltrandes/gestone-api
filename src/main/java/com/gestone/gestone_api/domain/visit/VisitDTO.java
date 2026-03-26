package com.gestone.gestone_api.domain.visit;

import java.time.LocalDateTime;
import java.util.UUID;

public record VisitDTO(
        UUID customerId,
        VisitReason reason,
        LocalDateTime scheduledAt,
        String notes
) {
}
