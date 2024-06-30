package com.gestone.gestone_api.domain.quotation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuotationRepository extends JpaRepository<Quotation, UUID> {
}
