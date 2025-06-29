package com.gestone.gestone_api.domain.production_order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/production")
public class ProductionOrderController {

    @Autowired
    private ProductionOrderService productionOrderService;
    @PostMapping
    public ResponseEntity<ProductionOrderResponseDTO> saveProductionOrder(@RequestBody ProductionOrderRequestDTO productionOrderRequestDTO) {
        ProductionOrder productionOrder = productionOrderService.save(productionOrderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductionOrderResponseDTO(productionOrder));
    }
}
