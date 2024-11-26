package com.gestone.gestone_api.domain.material;

import java.math.BigDecimal;
import java.util.UUID;

public class MiscellaneousMaterialResponseDTO {
    private UUID id;
    private String name;
    private String details;
    private BigDecimal price;
    private BigDecimal lastPrice;
    private MiscellaneousMaterialType miscellaneousMaterialType;

    public MiscellaneousMaterialResponseDTO(MiscellaneousMaterial miscellaneousMaterial) {
        this.id = miscellaneousMaterial.getId();
        this.name = miscellaneousMaterial.getName();
        this.details = miscellaneousMaterial.getDetails();
        this.price = miscellaneousMaterial.getPrice();
        this.lastPrice = miscellaneousMaterial.getLastPrice();
        this.miscellaneousMaterialType = miscellaneousMaterial.getMiscellaneousMaterialType();
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

    public MiscellaneousMaterialType getMiscellaneousMaterialType() {
        return miscellaneousMaterialType;
    }

    public void setMiscellaneousMaterialType(MiscellaneousMaterialType miscellaneousMaterialType) {
        this.miscellaneousMaterialType = miscellaneousMaterialType;
    }
}
