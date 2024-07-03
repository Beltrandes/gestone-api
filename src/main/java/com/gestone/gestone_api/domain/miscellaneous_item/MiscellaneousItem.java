package com.gestone.gestone_api.domain.miscellaneous_item;

import com.gestone.gestone_api.domain.marbleshop_order.MarbleshopOrder;
import com.gestone.gestone_api.domain.quotation.Quotation;
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
    private BigDecimal unitValue = BigDecimal.ZERO;
    private BigDecimal totalValue = BigDecimal.ZERO;
    @OneToOne
    private MiscellaneousMaterial miscellaneousMaterial;
    @ManyToOne(fetch = FetchType.LAZY)
    private Quotation quotation;
    @ManyToOne(fetch = FetchType.LAZY)
    private MarbleshopOrder marbleshopOrder;

    public MiscellaneousItem() {
    }

    public MiscellaneousItem(String name, String details, Integer quantity, MiscellaneousMaterial miscellaneousMaterial) {
        this.name = name;
        this.details = details;
        this.quantity = quantity;
        this.miscellaneousMaterial = miscellaneousMaterial;
    }

    public void calculate() {
        unitValue = miscellaneousMaterial.getPrice();
        totalValue = unitValue.multiply(BigDecimal.valueOf(quantity));
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

    public BigDecimal getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
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

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public MarbleshopOrder getMarbleshopOrder() {
        return marbleshopOrder;
    }

    public void setMarbleshopOrder(MarbleshopOrder marbleshopOrder) {
        this.marbleshopOrder = marbleshopOrder;
    }
}
