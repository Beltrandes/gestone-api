package com.gestone.gestone_api.domain.material;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MiscellaneousMaterialRepository extends JpaRepository<MiscellaneousMaterial, UUID> {
}
