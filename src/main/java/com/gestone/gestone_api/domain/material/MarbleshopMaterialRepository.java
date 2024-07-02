package com.gestone.gestone_api.domain.material;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MarbleshopMaterialRepository extends JpaRepository<MarbleshopMaterial, UUID> {
}
