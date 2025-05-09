package com.gestone.gestone_api.domain.marbleshop_item;

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

    private String name;
    private String description;
    private BigDecimal measureX = BigDecimal.ZERO;
    private BigDecimal measureY = BigDecimal.ZERO;
    private Integer quantity;
    private BigDecimal value = BigDecimal.ZERO;
    private BigDecimal totalValue = BigDecimal.ZERO;
    private BigDecimal area = BigDecimal.ZERO;
    private BigDecimal totalArea = BigDecimal.ZERO;
    @Enumerated(value = EnumType.STRING)
    private MarbleshopSubItemType marbleshopSubItemType;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MarbleshopItem marbleshopItem;

    @CreationTimestamp
    private LocalDateTime createdAt;


    public MarbleshopSubItem() {
    }


    public MarbleshopSubItem(String description,BigDecimal measureX, BigDecimal measureY,Integer quantity,MarbleshopSubItemType marbleshopSubItemType, MarbleshopItem marbleshopItem) {
        this.description = description;
        this.measureX = measureX;
        this.measureY = measureY;
        this.quantity = quantity;
        this.marbleshopSubItemType = marbleshopSubItemType;
        this.marbleshopItem = marbleshopItem;
    }

    public void calculate() {
        this.area = this.measureX.multiply(this.measureY);
        this.totalArea = this.area.multiply(BigDecimal.valueOf(this.quantity));
        if (this.marbleshopItem != null) {
            this.value = this.area.multiply(this.marbleshopItem.getMarbleshopMaterial().getPrice());
            this.totalValue = this.value.multiply(BigDecimal.valueOf(this.quantity));
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(BigDecimal totalArea) {
        this.totalArea = totalArea;
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
