package com.gestone.gestone_api.domain.marbleshop_item;

import com.gestone.gestone_api.domain.material.MarbleshopMaterial;
import com.gestone.gestone_api.domain.material.MarbleshopMaterialResponseDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class MarbleshopItemResponseDTO {
    UUID id;
    String name;
    String description;
    BigDecimal measureX;
    BigDecimal measureY;
    BigDecimal unitValue;
    BigDecimal unitArea;
    BigDecimal totalValue;
    BigDecimal totalArea;
    Integer quantity;

    MarbleshopItemType marbleshopItemType;
    MarbleshopMaterialResponseDTO marbleshopMaterial;
    List<MarbleshopSubItemResponseDTO> marbleshopSubItems;


    public MarbleshopItemResponseDTO(MarbleshopItem marbleshopItem) {
        this.id = marbleshopItem.getId();
        this.name = marbleshopItem.getName();
        this.description = marbleshopItem.getDescription();
        this.measureX = marbleshopItem.getMeasureX();
        this.measureY = marbleshopItem.getMeasureY();
        this.unitValue = marbleshopItem.getUnitValue();
        this.unitArea = marbleshopItem.getUnitArea();
        this.totalValue = marbleshopItem.getTotalValue();
        this.totalArea = marbleshopItem.getTotalArea();
        this.quantity = marbleshopItem.getQuantity();
        this.marbleshopItemType = marbleshopItem.getMarbleshopItemType();
        this.marbleshopMaterial = new MarbleshopMaterialResponseDTO(marbleshopItem.getMarbleshopMaterial());
        this.marbleshopSubItems = marbleshopItem.getMarbleshopSubItems().stream().map(MarbleshopSubItemResponseDTO::new).toList();
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

    public BigDecimal getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    public BigDecimal getUnitArea() {
        return unitArea;
    }

    public void setUnitArea(BigDecimal unitArea) {
        this.unitArea = unitArea;
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

    public MarbleshopMaterialResponseDTO getMarbleshopMaterial() {
        return marbleshopMaterial;
    }

    public List<MarbleshopSubItemResponseDTO> getMarbleshopSubItems() {
        return marbleshopSubItems;
    }

    public void setMarbleshopSubItems(List<MarbleshopSubItemResponseDTO> marbleshopSubItems) {
        this.marbleshopSubItems = marbleshopSubItems;
    }

    public MarbleshopItemType getMarbleshopItemType() {
        return marbleshopItemType;
    }

    public void setMarbleshopItemType(MarbleshopItemType marbleshopItemType) {
        this.marbleshopItemType = marbleshopItemType;
    }

    public void setMarbleshopMaterial(MarbleshopMaterialResponseDTO marbleshopMaterial) {
        this.marbleshopMaterial = marbleshopMaterial;
    }
}
