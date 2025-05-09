package com.gestone.gestone_api.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MarbleshopOrderRepository extends JpaRepository<MarbleshopOrder, UUID> {
    List<MarbleshopOrder> findByMarbleshop_Id(UUID marbleshopId);
    List<MarbleshopOrder> findByMarbleshop_IdAndCustomer_Id(UUID marbleshopId, UUID customerId);

    @Query("SELECT MAX(o.localId) FROM MarbleshopOrder o WHERE o.marbleshop.id = :marbleshopId")
    Integer findMaxLocalIdByMarbleshop(@Param("marbleshopId") UUID marbleshopId);

}
