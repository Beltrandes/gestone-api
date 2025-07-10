package com.gestone.gestone_api.domain.production_order;

import com.gestone.gestone_api.domain.order.MarbleshopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, UUID> {
    List<ProductionOrder> findByMarbleshopId(UUID marbleshopId);
}
