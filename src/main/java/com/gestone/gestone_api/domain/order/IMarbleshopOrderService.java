package com.gestone.gestone_api.domain.order;


import java.util.List;
import java.util.UUID;

public interface IMarbleshopOrderService {
    MarbleshopOrder save(MarbleshopOrderDTO marbleshopOrderDTO);
   List<MarbleshopOrder> findAll(UUID marbleshopId);

    MarbleshopOrder findById(UUID id);
    void updateStatus(UUID id, com.gestone.gestone_api.domain.order.MarbleshopOrderStatus status);
}
