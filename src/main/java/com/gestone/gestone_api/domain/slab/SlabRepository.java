package com.gestone.gestone_api.domain.slab;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SlabRepository extends JpaRepository<Slab, UUID> {
}
