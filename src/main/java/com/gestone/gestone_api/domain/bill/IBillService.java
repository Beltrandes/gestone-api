package com.gestone.gestone_api.domain.bill;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

public interface IBillService {
    Bill save(BillRequestDTO billRequestDTO, HttpServletRequest request);

    List<Bill> findAll(UUID marbleshopId);

    Bill update(BillRequestDTO billRequestDTO, UUID billId);

    void delete(UUID billId);
}
