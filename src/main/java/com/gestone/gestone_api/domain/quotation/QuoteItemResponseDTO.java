package com.gestone.gestone_api.domain.quotation;

import com.gestone.gestone_api.domain.material.MaterialResponseDTO;

import java.math.BigDecimal;
import java.util.UUID;

public class QuoteItemResponseDTO {

    private UUID id;
    private String name;
    private String details;
    private BigDecimal measureX;
    private  BigDecimal measureY;
    private Integer quantity;
    private MaterialResponseDTO material;
    private BigDecimal value;
    private BigDecimal area;
    private BigDecimal totalValue;
    private BigDecimal totalArea;

    public QuoteItemResponseDTO(QuoteItem quoteItem) {
        this.id = quoteItem.getId();
        this.name = quoteItem.getName();
        this.details = quoteItem.getDetails();
        this.measureX = quoteItem.getMeasureX();
        this.measureY = quoteItem.getMeasureY();
        this.quantity = quoteItem.getQuantity();
        this.material = new MaterialResponseDTO(quoteItem.getMaterial());
        this.value = quoteItem.getValue();
        this.area = quoteItem.getArea();
        this.totalValue = quoteItem.getTotalValue();
        this.totalArea = quoteItem.getTotalArea();
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MaterialResponseDTO getMaterial() {
        return material;
    }

    public void setMaterial(MaterialResponseDTO material) {
        this.material = material;
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
}
