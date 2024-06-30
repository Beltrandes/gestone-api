package com.gestone.gestone_api.domain.quotation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuoteItemRepository extends JpaRepository<QuoteItem, UUID> {
}
