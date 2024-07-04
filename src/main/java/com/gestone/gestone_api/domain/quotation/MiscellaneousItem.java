package com.gestone.gestone_api.domain.quotation;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

import com.gestone.gestone_api.domain.material.MiscellaneousMaterial;
@Entity
public class MiscellaneousItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String details;
    private Integer quantity;
    private BigDecimal value = BigDecimal.ZERO;
    private BigDecimal totalValue = BigDecimal.ZERO;
    @OneToOne
    private MiscellaneousMaterial miscellaneousMaterial;
    @ManyToOne
    private MarbleshopItem marbleshopItem;

    public MiscellaneousItem() {
    }

    public MiscellaneousItem(String name, String details, Integer quantity, MiscellaneousMaterial miscellaneousMaterial,
            MarbleshopItem marbleshopItem) {
        this.name = name;
        this.details = details;
        this.quantity = quantity;
        this.miscellaneousMaterial = miscellaneousMaterial;
        this.marbleshopItem = marbleshopItem;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public MiscellaneousMaterial getMiscellaneousMaterial() {
        return miscellaneousMaterial;
    }

    public void setMiscellaneousMaterial(MiscellaneousMaterial miscellaneousMaterial) {
        this.miscellaneousMaterial = miscellaneousMaterial;
    }

    public MarbleshopItem getMarbleshopItem() {
        return marbleshopItem;
    }

    public void setMarbleshopItem(MarbleshopItem marbleshopItem) {
        this.marbleshopItem = marbleshopItem;
    }

}
