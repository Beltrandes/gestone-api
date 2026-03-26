package com.gestone.gestone_api.domain.visit;

import java.time.LocalDateTime;
import java.util.UUID;

public record VisitResponseDTO(
        UUID id,
        UUID customerId,
        String customerName,
        VisitReason reason,
        LocalDateTime scheduledAt,
        String notes,
        boolean completed,
        LocalDateTime completedAt,
        LocalDateTime createdAt
) {
    public VisitResponseDTO(Visit visit) {
        this(
                visit.getId(),
                visit.getCustomer().getId(),
                visit.getCustomer().getName(),
                visit.getReason(),
                visit.getScheduledAt(),
                visit.getNotes(),
                visit.isCompleted(),
                visit.getCompletedAt(),
                visit.getCreatedAt()
        );
    }
}
