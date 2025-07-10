package com.gestone.gestone_api.domain.slab;

import com.gestone.gestone_api.domain.production_order.ProductionOrderItem;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class SlabMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Slab slab;
    private BigDecimal areaMoved;
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductionOrderItem productionOrderItem;
    @Enumerated(EnumType.STRING)
    private SlabMovementType movementType;
    @CreationTimestamp
    private LocalDateTime createdAt;

    private String notes;

    public SlabMovement() {
    }

    public Slab getSlab() {
        return slab;
    }

    public void setSlab(Slab slab) {
        this.slab = slab;
    }

    public BigDecimal getAreaMoved() {
        return areaMoved;
    }

    public void setAreaMoved(BigDecimal areaMoved) {
        this.areaMoved = areaMoved;
    }

    public ProductionOrderItem getProductionOrderItem() {
        return productionOrderItem;
    }

    public void setProductionOrderItem(ProductionOrderItem productionOrderItem) {
        this.productionOrderItem = productionOrderItem;
    }

    public SlabMovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(SlabMovementType movementType) {
        this.movementType = movementType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void calculateAreaMoved() {
        this.areaMoved = this.getProductionOrderItem().getMarbleshopItem().getTotalArea();
    }

    public UUID getId() {
        return id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
