package com.gestone.gestone_api.domain.installation_order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InstallationOrderRepository extends JpaRepository<InstallationOrder, UUID> {
    List<InstallationOrder> findAllByMarbleshopId(UUID marbleshopId);
    InstallationOrder findByOrderId(UUID orderId);
}
