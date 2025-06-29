package com.gestone.gestone_api.domain.production_order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductionOrderItemRepository extends JpaRepository<ProductionOrderItem, UUID> {
}
