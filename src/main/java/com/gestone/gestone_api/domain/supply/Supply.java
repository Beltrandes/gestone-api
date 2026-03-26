package com.gestone.gestone_api.domain.supply;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Supply {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    private String unitOfMeasure; // e.g., UN, KG, LATA

    private Integer minimumStock = 0;

    private Integer currentStock = 0;

    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marbleshop_id", nullable = false)
    private Marbleshop marbleshop;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Supply() {
    }

    public Supply(String name, String description, String unitOfMeasure, Integer minimumStock, Marbleshop marbleshop) {
        this.name = name;
        this.description = description;
        this.unitOfMeasure = unitOfMeasure;
        this.minimumStock = minimumStock != null ? minimumStock : 0;
        this.marbleshop = marbleshop;
    }

    public UUID getId() { return id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getUnitOfMeasure() { return unitOfMeasure; }
    public void setUnitOfMeasure(String unitOfMeasure) { this.unitOfMeasure = unitOfMeasure; }

    public Integer getMinimumStock() { return minimumStock; }
    public void setMinimumStock(Integer minimumStock) { this.minimumStock = minimumStock; }

    public Integer getCurrentStock() { return currentStock; }
    public void setCurrentStock(Integer currentStock) { this.currentStock = currentStock; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Marbleshop getMarbleshop() { return marbleshop; }
    public void setMarbleshop(Marbleshop marbleshop) { this.marbleshop = marbleshop; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
