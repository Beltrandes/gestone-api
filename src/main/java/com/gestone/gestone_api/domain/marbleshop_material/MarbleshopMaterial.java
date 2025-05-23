package com.gestone.gestone_api.domain.marbleshop_material;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class MarbleshopMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String details;
    private BigDecimal price;
    private BigDecimal lastPrice = BigDecimal.ZERO;
    private MarbleshopMaterialType marbleshopMaterialType;

    private boolean active = true;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private Marbleshop marbleshop;

    public MarbleshopMaterial() {
    }

    public MarbleshopMaterial(String name, String details, BigDecimal price, MarbleshopMaterialType marbleshopMaterialType, Marbleshop marbleshop) {
        this.name = name;
        this.details = details;
        this.price = price;
        this.marbleshopMaterialType = marbleshopMaterialType;
        this.marbleshop = marbleshop;
    }

    public UUID getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    public MarbleshopMaterialType getMaterialType() {
        return marbleshopMaterialType;
    }

    public void setMaterialType(MarbleshopMaterialType marbleshopMaterialType) {
        this.marbleshopMaterialType = marbleshopMaterialType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Marbleshop getMarbleshop() {
        return marbleshop;
    }

    public void setMarbleshop(Marbleshop marbleshop) {
        this.marbleshop = marbleshop;
    }

    public MarbleshopMaterialType getMarbleshopMaterialType() {
        return marbleshopMaterialType;
    }

    public void setMarbleshopMaterialType(MarbleshopMaterialType marbleshopMaterialType) {
        this.marbleshopMaterialType = marbleshopMaterialType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
