package com.gestone.gestone_api.domain.material;

import java.math.BigDecimal;
import java.util.UUID;

public class MaterialResponseDTO {

    private UUID id;
    private String name;
    private String details;
    private BigDecimal price;
    private MaterialType materialType;

    public MaterialResponseDTO(Material material) {
        this.id = material.getId();
        this.name = material.getName();
        this.details = material.getDetails();
        this.price = material.getPrice();
        this.materialType = material.getMaterialType();
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

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }
}
