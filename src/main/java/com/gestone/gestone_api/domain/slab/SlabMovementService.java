package com.gestone.gestone_api.domain.slab;

import com.gestone.gestone_api.domain.production_order.ProductionOrderItem;
import com.gestone.gestone_api.domain.production_order.ProductionOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlabMovementService {
    @Autowired
    private SlabMovementRepository slabMovementRepository;
    @Autowired
    private SlabService slabService;
    @Autowired
    private ProductionOrderItemService productionOrderItemService;

    public SlabMovement save(SlabMovementRequestDTO slabMovementRequestDTO) {
        SlabMovement slabMovement = new SlabMovement();
        ProductionOrderItem productionOrderItem = productionOrderItemService.findById(slabMovementRequestDTO.productionOrderItemId());
        Slab slab = slabService.findById(slabMovementRequestDTO.slabId());
        slabMovement.setSlab(slab);
        slabMovement.setMovementType(slabMovementRequestDTO.movementType());
        slabMovement.setAreaMoved(slabMovementRequestDTO.areaMoved());
        slabMovement.setProductionOrderItem(productionOrderItem);
        slabMovement.setNotes(slabMovementRequestDTO.notes());
        slabMovement.calculateAreaMoved();
        return slabMovementRepository.save(slabMovement);
    }
}
