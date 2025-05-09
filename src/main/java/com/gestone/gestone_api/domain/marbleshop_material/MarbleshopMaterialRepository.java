package com.gestone.gestone_api.domain.marbleshop_material;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MarbleshopMaterialRepository extends JpaRepository<MarbleshopMaterial, UUID> {

    List<MarbleshopMaterial> findByMarbleshop_IdAndActiveTrue(UUID marbleshopId);
}
