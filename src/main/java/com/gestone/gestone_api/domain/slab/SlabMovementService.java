package com.gestone.gestone_api.domain.slab;

import com.gestone.gestone_api.domain.production_order.ProductionOrderItem;
import com.gestone.gestone_api.domain.production_order.ProductionOrderItemService;
import com.gestone.gestone_api.domain.production_order.ProductionOrderItemStatus;
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
        Slab slab = slabService.findById(slabMovementRequestDTO.slabId());
        
        slabMovement.setSlab(slab);
        slabMovement.setMovementType(slabMovementRequestDTO.movementType());
        if (slabMovementRequestDTO.productionOrderItemId() != null) {
            ProductionOrderItem productionOrderItem = productionOrderItemService.findById(slabMovementRequestDTO.productionOrderItemId());
            productionOrderItem.setStatus(ProductionOrderItemStatus.CUT);
            slabMovement.setProductionOrderItem(productionOrderItem);
            slabMovement.calculateAreaMoved();
        } else if (slabMovementRequestDTO.areaMoved() != null) {
            // Manual movement without production order
            slabMovement.setAreaMoved(slabMovementRequestDTO.areaMoved());
        }

        // Subtract area for OUT movements, add for IN movements
        if (slabMovementRequestDTO.movementType() == SlabMovementType.CUT || 
            slabMovementRequestDTO.movementType() == SlabMovementType.PRODUCTION_OUT || 
            slabMovementRequestDTO.movementType() == SlabMovementType.SALE || 
            slabMovementRequestDTO.movementType() == SlabMovementType.DISCARD) {
            slab.setArea(slab.getArea().subtract(slabMovement.getAreaMoved()));
        } else if (slabMovementRequestDTO.movementType() == SlabMovementType.PRODUCTION_IN ||
                   slabMovementRequestDTO.movementType() == SlabMovementType.ENTRY) {
            slab.setArea(slab.getArea().add(slabMovement.getAreaMoved()));
        }

        slabService.updateStatusBasedOnArea(slab);

        slabMovement.setNotes(slabMovementRequestDTO.notes());
        return slabMovementRepository.save(slabMovement);
    }

    public java.util.List<SlabMovement> findAllBySlabId(java.util.UUID slabId) {
        return slabMovementRepository.findAllBySlabIdOrderByCreatedAtDesc(slabId);
    }
}
