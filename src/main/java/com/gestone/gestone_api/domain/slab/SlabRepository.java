package com.gestone.gestone_api.domain.slab;

import com.gestone.gestone_api.domain.order.MarbleshopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SlabRepository extends JpaRepository<Slab, UUID> {
    List<Slab> findByMarbleshopIdAndActiveTrue(UUID marbleshopId);
}
