package com.gestone.gestone_api.domain.quotation;

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
    UUID marbleshopMaterialId;
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
        this.marbleshopMaterialId = marbleshopItem.getMarbleshopMaterial().getId();
        this.marbleshopSubItems = marbleshopItem.getMarbleshopSubItems().stream().map(MarbleshopSubItemResponseDTO::new).toList();
    }

}
