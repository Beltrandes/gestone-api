package com.gestone.gestone_api.domain.marbleshop_item;

import com.gestone.gestone_api.domain.material.MarbleshopMaterial;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class MarbleshopSubItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private BigDecimal measureX = BigDecimal.ZERO;
    private BigDecimal measureY = BigDecimal.ZERO;
    private Integer quantity;
    private BigDecimal value = BigDecimal.ZERO;
    private BigDecimal area = BigDecimal.ZERO;
    @OneToOne
    private MarbleshopMaterial marbleshopMaterial;
    @Enumerated(value = EnumType.STRING)
    private MarbleshopSubItemType marbleshopSubItemType;
    @ManyToOne(fetch = FetchType.LAZY)
    private MarbleshopItem marbleshopItem;

    @CreationTimestamp
    private LocalDateTime createdAt;


    public MarbleshopSubItem() {
    }


    public MarbleshopSubItem(BigDecimal measureX, BigDecimal measureY,Integer quantity,MarbleshopSubItemType marbleshopSubItemType, MarbleshopItem marbleshopItem) {
        this.measureX = measureX;
        this.measureY = measureY;
        this.quantity = quantity;
        this.marbleshopSubItemType = marbleshopSubItemType;
        this.marbleshopItem = marbleshopItem;
    }

    public void calculate() {
        this.area = this.measureX.multiply(this.measureY);
        this.value = this.area.multiply(this.marbleshopMaterial.getPrice());
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getMeasureX() {
        return measureX;
    }

    public void setMeasureX(BigDecimal measureX) {
        this.measureX = measureX;
    }

    public BigDecimal getMeasureY() {
        return measureY;
    }

    public void setMeasureY(BigDecimal measureY) {
        this.measureY = measureY;
    }

    public MarbleshopSubItemType getMarbleshopSubItemType() {
        return marbleshopSubItemType;
    }

    public void setMarbleshopSubItemType(MarbleshopSubItemType marbleshopSubItemType) {
        this.marbleshopSubItemType = marbleshopSubItemType;
    }

    public MarbleshopItem getMarbleshopItem() {
        return marbleshopItem;
    }

    public void setMarbleshopItem(MarbleshopItem marbleshopItem) {
        this.marbleshopItem = marbleshopItem;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }


    public MarbleshopMaterial getMarbleshopMaterial() {
        return marbleshopMaterial;
    }

    public void setMarbleshopMaterial(MarbleshopMaterial marbleshopMaterial) {
        this.marbleshopMaterial = marbleshopMaterial;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
