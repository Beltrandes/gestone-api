package com.gestone.gestone_api.domain.material;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MiscellaneousMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String details;
    private BigDecimal price = BigDecimal.ZERO;
    private BigDecimal lastPrice = BigDecimal.ZERO;
    @Enumerated(value = EnumType.STRING)
    private MiscellaneousMaterialType miscellaneousMaterialType;

    public MiscellaneousMaterial() {
    }

    public MiscellaneousMaterial(String name, String details, BigDecimal price,
            MiscellaneousMaterialType miscellaneousMaterialType) {
        this.name = name;
        this.details = details;
        this.price = price;
        this.miscellaneousMaterialType = miscellaneousMaterialType;
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
