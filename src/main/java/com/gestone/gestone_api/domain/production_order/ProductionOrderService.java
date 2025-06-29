package com.gestone.gestone_api.domain.production_order;

import com.gestone.gestone_api.domain.order.MarbleshopOrder;
import com.gestone.gestone_api.domain.order.MarbleshopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductionOrderService {
    @Autowired
    private ProductionOrderRepository productionOrderRepository;
    @Autowired
    private MarbleshopOrderService marbleshopOrderService;

    public ProductionOrder save(ProductionOrderRequestDTO productionOrderRequestDTO) {
        ProductionOrder productionOrder = new ProductionOrder();
        MarbleshopOrder order = marbleshopOrderService.findById(productionOrderRequestDTO.marbleshopOrderId());
        List<ProductionOrderItem> productionOrderItemList = order.getMarbleshopItems().stream().map((marbleshopItem) -> {
            ProductionOrderItem productionOrderItem = new ProductionOrderItem();
            productionOrderItem.setProductionOrder(productionOrder);
            productionOrderItem.setMarbleshopItem(marbleshopItem);
            productionOrderItem.setNotes(marbleshopItem.getDescription());
            return productionOrderItem;
        }).toList();
        productionOrder.setProductionOrderItems(productionOrderItemList);
        productionOrder.setProductionPriority(productionOrderRequestDTO.productionPriority());
        productionOrder.setExpectedStartDate(productionOrderRequestDTO.expectedStartDate());
        productionOrder.setExpectedEndDate(productionOrderRequestDTO.expectedEndDate());
        productionOrder.setNotes(productionOrderRequestDTO.notes());
        productionOrder.setOrder(order);
        return productionOrderRepository.save(productionOrder);
    }
}
