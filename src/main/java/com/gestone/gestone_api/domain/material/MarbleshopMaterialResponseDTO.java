package com.gestone.gestone_api.domain.material;

import java.math.BigDecimal;
import java.util.UUID;

public class MarbleshopMaterialResponseDTO {

    private UUID id;
    private String name;
    private String details;
    private BigDecimal price;
    private BigDecimal lastPrice;
    private MarbleshopMaterialType marbleshopMaterialType;

    public MarbleshopMaterialResponseDTO(MarbleshopMaterial marbleshopMaterial) {
        this.id = marbleshopMaterial.getId();
        this.name = marbleshopMaterial.getName();
        this.details = marbleshopMaterial.getDetails();
        this.price = marbleshopMaterial.getPrice();
        this.lastPrice = marbleshopMaterial.getLastPrice();
        this.marbleshopMaterialType = marbleshopMaterial.getMaterialType();
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
}
