package com.gestone.gestone_api.domain.production_order;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.order.MarbleshopOrder;
import com.gestone.gestone_api.domain.order.MarbleshopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductionOrderService {
    @Autowired
    private ProductionOrderRepository productionOrderRepository;
    @Autowired
    private ProductionOrderItemRepository productionOrderItemRepository;
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
        productionOrder.setMarbleshop(order.getMarbleshop());
        productionOrder.setRealStartDate(LocalDate.now());

        marbleshopOrderService.updateStatus(order.getId(), com.gestone.gestone_api.domain.order.MarbleshopOrderStatus.PRODUCING);

        return productionOrderRepository.save(productionOrder);
    }

    public ProductionOrder updateItemStatus(UUID itemId, ProductionOrderItemStatus newStatus) {
        ProductionOrderItem item = productionOrderItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Production Order Item not found"));
        item.setStatus(newStatus);
        productionOrderItemRepository.save(item);

        ProductionOrder order = item.getProductionOrder();
        
        boolean allProduced = order.getProductionOrderItems().stream()
                .allMatch(i -> i.getStatus() == ProductionOrderItemStatus.PRODUCED);

        if (allProduced && order.getProductionStatus() != ProductionStatus.FINISHED) {
            updateStatus(order.getId(), ProductionStatus.FINISHED);
        }

        return order;
    }

    public ProductionOrder updateStatus(UUID id, ProductionStatus newStatus) {
        ProductionOrder productionOrder = findById(id);
        productionOrder.setProductionStatus(newStatus);
        
        if (newStatus == ProductionStatus.FINISHED) {
            productionOrder.setRealEndDate(LocalDate.now());
            marbleshopOrderService.updateStatus(productionOrder.getOrder().getId(), com.gestone.gestone_api.domain.order.MarbleshopOrderStatus.PRODUCED);
        }
        
        return productionOrderRepository.save(productionOrder);
    }

    public void uploadProject(UUID id, MultipartFile file) throws IOException {
        ProductionOrder productionOrder = findById(id);
        String uploadDir = "/uploads/projects/";
        String fileName = id + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        productionOrder.getProjectUrls().add(fileName);
        productionOrderRepository.save(productionOrder);

    }

    public ProductionOrder findById(UUID id) {
        return this.productionOrderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Production Order not found with id: " + id));
    }

    public List<ProductionOrder> findAll(UUID marbleshopId) {
        return this.productionOrderRepository.findByMarbleshopId(marbleshopId);
    }
}
