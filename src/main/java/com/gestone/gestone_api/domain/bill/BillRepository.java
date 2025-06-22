package com.gestone.gestone_api.domain.bill;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BillRepository  extends JpaRepository<Bill, UUID> {

    List<Bill> findByMarbleshopId(UUID marbleshopId);
}
