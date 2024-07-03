package com.gestone.gestone_api.domain.miscellaneous_item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MiscellaneousItemRepository extends JpaRepository<MiscellaneousItem, UUID> {
}
