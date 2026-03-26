package com.gestone.gestone_api.domain.miscellaneous_item;

import com.gestone.gestone_api.domain.order.MarbleshopOrder;
import com.gestone.gestone_api.domain.quotation.Quotation;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import com.gestone.gestone_api.domain.miscellaneous_material.MiscellaneousMaterial;
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
    private BigDecimal materialPriceSnapshot;

    @ManyToOne(fetch = FetchType.LAZY)
    private MiscellaneousMaterial miscellaneousMaterial;
    @ManyToOne(fetch = FetchType.LAZY)
    private Quotation quotation;

    @ManyToOne(fetch = FetchType.LAZY)
    private MarbleshopOrder marbleshopOrder;

    public MiscellaneousItem() {
    }

    public void calculate() {
        if (this.materialPriceSnapshot == null && this.miscellaneousMaterial != null && this.miscellaneousMaterial.getPrice() != null) {
            this.materialPriceSnapshot = this.miscellaneousMaterial.getPrice();
        }

        if (this.materialPriceSnapshot != null) {
            this.unitValue = this.materialPriceSnapshot.setScale(2, RoundingMode.HALF_UP);
        } else {
            this.unitValue = BigDecimal.ZERO;
        }
        
        if (this.quantity != null) {
            this.totalValue = this.unitValue.multiply(BigDecimal.valueOf(this.quantity)).setScale(2, RoundingMode.HALF_UP);
        } else {
            this.totalValue = BigDecimal.ZERO;
        }
    }

    public BigDecimal getMaterialPriceSnapshot() {
        return materialPriceSnapshot;
    }

    public void setMaterialPriceSnapshot(BigDecimal materialPriceSnapshot) {
        this.materialPriceSnapshot = materialPriceSnapshot;
    }

    public MiscellaneousItem(String name, String details, Integer quantity, MiscellaneousMaterial miscellaneousMaterial,
            Quotation quotation) {
        this.name = name;
        this.details = details;
        this.quantity = quantity;
        this.miscellaneousMaterial = miscellaneousMaterial;
        this.quotation = quotation;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MiscellaneousItem that = (MiscellaneousItem) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
