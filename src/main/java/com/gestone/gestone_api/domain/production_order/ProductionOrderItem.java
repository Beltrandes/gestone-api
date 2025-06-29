package com.gestone.gestone_api.domain.production_order;

import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopItem;
import com.gestone.gestone_api.domain.slab.Slab;
import com.gestone.gestone_api.domain.slab.SlabMovement;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
public class ProductionOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductionOrder productionOrder;

    @OneToOne
    private MarbleshopItem marbleshopItem;

    @OneToMany(mappedBy = "productionOrderItem")
    private List<SlabMovement> slabMovements = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private ProductionOrderItemStatus status = ProductionOrderItemStatus.WAITING_FOR_CUT;

    private String notes;

    public ProductionOrder getProductionOrder() {
        return productionOrder;
    }

    public void setProductionOrder(ProductionOrder productionOrder) {
        this.productionOrder = productionOrder;
    }

    public MarbleshopItem getMarbleshopItem() {
        return marbleshopItem;
    }

    public void setMarbleshopItem(MarbleshopItem marbleshopItem) {
        this.marbleshopItem = marbleshopItem;
    }

    public List<SlabMovement> getSlabMovements() {
        return slabMovements;
    }

    public void setSlabMovements(List<SlabMovement> slabMovements) {
        this.slabMovements = slabMovements;
    }

    public ProductionOrderItemStatus getStatus() {
        return status;
    }

    public void setStatus(ProductionOrderItemStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public UUID getId() {
        return id;
    }
}
