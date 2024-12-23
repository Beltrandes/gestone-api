package com.gestone.gestone_api.domain.quotation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface QuotationRepository extends JpaRepository<Quotation, UUID> {

    List<Quotation> findAllByMarbleshopId(UUID marbleshopId);

    @Query("SELECT MAX(q.localId) FROM Quotation q WHERE q.marbleshop.id = :marbleshopId")
    Integer findMaxLocalIdByMarbleshop(@Param("marbleshopId") UUID marbleshopId);
}
