package com.gestone.gestone_api.domain.marbleshop_item;

import java.math.BigDecimal;
import java.util.UUID;

public class MarbleshopSubItemResponseDTO {

    UUID id;
    String name;
    String description;
    BigDecimal measureX;
    BigDecimal measureY;
    Integer quantity;

    MarbleshopSubItemType marbleshopSubItemType;
    BigDecimal value;
    BigDecimal area;
    BigDecimal totalValue;
    BigDecimal totalArea;

    public MarbleshopSubItemResponseDTO(MarbleshopSubItem marbleshopSubItem) {
        this.id = marbleshopSubItem.getId();
        this.name = marbleshopSubItem.getName();
        this.description = marbleshopSubItem.getDescription();
        this.measureX = marbleshopSubItem.getMeasureX();
        this.measureY = marbleshopSubItem.getMeasureY();
        this.quantity = marbleshopSubItem.getQuantity();
        this.marbleshopSubItemType = marbleshopSubItem.getMarbleshopSubItemType();
        this.value = marbleshopSubItem.getValue();
        this.area = marbleshopSubItem.getArea();
        this.totalValue = marbleshopSubItem.getTotalValue();
        this.totalArea = marbleshopSubItem.getTotalArea();
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

    public MarbleshopSubItemType getMarbleshopSubItemType() {
        return marbleshopSubItemType;
    }

    public void setMarbleshopSubItemType(MarbleshopSubItemType marbleshopSubItemType) {
        this.marbleshopSubItemType = marbleshopSubItemType;
    }

    public void setMeasureY(BigDecimal measureY) {
        this.measureY = measureY;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
}
