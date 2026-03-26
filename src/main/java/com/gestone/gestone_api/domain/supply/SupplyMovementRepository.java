package com.gestone.gestone_api.domain.supply;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface SupplyMovementRepository extends JpaRepository<SupplyMovement, UUID> {
    List<SupplyMovement> findAllBySupplyIdOrderByCreatedAtDesc(UUID supplyId);
}
