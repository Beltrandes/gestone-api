package com.gestone.gestone_api.domain.miscellaneous_material;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MiscellaneousMaterialRepository extends JpaRepository<MiscellaneousMaterial, UUID> {
    List<MiscellaneousMaterial> findByMarbleshop_IdAndActiveTrue(UUID marbleshopId);
}
