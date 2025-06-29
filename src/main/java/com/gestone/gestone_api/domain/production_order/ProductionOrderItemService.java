package com.gestone.gestone_api.domain.production_order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductionOrderItemService {
    @Autowired
    private ProductionOrderItemRepository productionOrderItemRepository;

    public ProductionOrderItem findById(UUID productionOrderItemId) {
        if (productionOrderItemId == null) {
            return null;
        }
        return productionOrderItemRepository.findById(productionOrderItemId).orElseThrow(() -> new IllegalArgumentException("Production Order Item not found with id: " + productionOrderItemId));
    }
}
