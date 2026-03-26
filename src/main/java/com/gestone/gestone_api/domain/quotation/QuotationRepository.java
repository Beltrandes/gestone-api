package com.gestone.gestone_api.domain.quotation;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface QuotationRepository extends JpaRepository<Quotation, UUID> {

    List<Quotation> findAllByMarbleshopId(UUID marbleshopId);

    @Query("SELECT MAX(q.localId) FROM Quotation q WHERE q.marbleshop.id = :marbleshopId")
    Integer findMaxLocalIdByMarbleshop(@Param("marbleshopId") UUID marbleshopId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE quotation SET quotation_status = :expiredStatus " +
            "WHERE quotation_status = :pendingStatus AND (created_at + (days_for_due || ' days')::interval) < :now",
    nativeQuery = true)
    void expireAllPastDue(
            @Param("now") LocalDateTime now,
            @Param("pendingStatus") String pending,
            @Param("expiredStatus") String expired
    );


}
