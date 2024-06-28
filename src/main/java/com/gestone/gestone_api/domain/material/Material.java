package com.gestone.gestone_api.domain.material;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String details;
    private BigDecimal price;
    private BigDecimal lastPrice;
    private MaterialType materialType;
    private LocalDateTime createdAt;
    @ManyToOne
    private Marbleshop marbleshop;

    public Material() {
    }

    public Material(String name, String details, BigDecimal price, MaterialType materialType, Marbleshop marbleshop) {
        this.name = name;
        this.details = details;
        this.price = price;
        this.materialType = materialType;
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

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Marbleshop getMarbleshop() {
        return marbleshop;
    }
}