package com.gestone.gestone_api.domain.marbleshop_item;

import com.gestone.gestone_api.domain.marbleshop_material.MarbleshopMaterial;
import com.gestone.gestone_api.domain.order.MarbleshopOrder;
import com.gestone.gestone_api.domain.quotation.Quotation;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class MarbleshopItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;
    private BigDecimal measureX = BigDecimal.ZERO;
    private BigDecimal measureY = BigDecimal.ZERO;
    private Integer quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    private MarbleshopMaterial marbleshopMaterial;
    private BigDecimal unitValue = BigDecimal.ZERO;
    private BigDecimal unitArea = BigDecimal.ZERO;
    private BigDecimal totalValue = BigDecimal.ZERO;
    private BigDecimal totalArea = BigDecimal.ZERO;
    private BigDecimal materialPriceSnapshot;

    @Enumerated(value = EnumType.STRING)
    private MarbleshopItemType marbleshopItemType;
    @OneToMany(mappedBy = "marbleshopItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MarbleshopSubItem> marbleshopSubItems = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Quotation quotation;
    @ManyToOne(fetch = FetchType.LAZY)
    private MarbleshopOrder marbleshopOrder;


    public MarbleshopItem() {
    }

    public MarbleshopItem(BigDecimal measureX, BigDecimal measureY, Integer quantity, MarbleshopMaterial marbleshopMaterial) {
        this.measureX = measureX;
        this.measureY = measureY;
        this.quantity = quantity;
        this.marbleshopMaterial = marbleshopMaterial;
    }

    public void calculate() {
        if (this.measureX != null && this.measureY != null) {
            this.unitArea = this.measureX.multiply(this.measureY);
        } else {
            this.unitArea = BigDecimal.ZERO;
        }

        if (this.materialPriceSnapshot == null && this.marbleshopMaterial != null && this.marbleshopMaterial.getPrice() != null) {
            this.materialPriceSnapshot = this.marbleshopMaterial.getPrice();
        }

        if (this.materialPriceSnapshot != null) {
            this.unitValue = this.unitArea.multiply(this.materialPriceSnapshot);
            this.unitValue = this.unitValue.setScale(2, RoundingMode.HALF_UP);
        } else {
            this.unitValue = BigDecimal.ZERO;
        }

        marbleshopSubItems.forEach(marbleshopSubItem -> {
            marbleshopSubItem.calculate();
            this.unitValue = this.unitValue.add(marbleshopSubItem.getTotalValue());
            this.unitValue = this.unitValue.setScale(2, RoundingMode.HALF_UP);
            this.unitArea = this.unitArea.add(marbleshopSubItem.getTotalArea());
        });

        if (this.quantity != null) {
            this.totalValue = this.unitValue.multiply(BigDecimal.valueOf(this.quantity));
            this.totalValue = this.totalValue.setScale(2, RoundingMode.HALF_UP);
            this.totalArea = this.unitArea.multiply(BigDecimal.valueOf(this.quantity));
        } else {
            this.totalValue = BigDecimal.ZERO;
            this.totalArea = BigDecimal.ZERO;
        }
    }

    public void calculateTotalValue() {
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


    public UUID getId() {
        return id;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MarbleshopMaterial getMarbleshopMaterial() {
        return marbleshopMaterial;
    }

    public void setMarbleshopMaterial(MarbleshopMaterial marbleshopMaterial) {
        this.marbleshopMaterial = marbleshopMaterial;
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

    public MarbleshopItemType getMarbleshopItemType() {
        return marbleshopItemType;
    }

    public void setMarbleshopItemType(MarbleshopItemType marbleshopItemType) {
        this.marbleshopItemType = marbleshopItemType;
    }

    public List<MarbleshopSubItem> getMarbleshopSubItems() {
        return marbleshopSubItems;
    }

    public void setMarbleshopSubItems(List<MarbleshopSubItem> marbleshopSubItems) {
        this.marbleshopSubItems = marbleshopSubItems;
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

    public void addMarbleshopSubItem(MarbleshopSubItem subItem) {
        this.marbleshopSubItems.add(subItem);
        subItem.setMarbleshopItem(this);
    }
    public void removeSubItem(MarbleshopSubItem subItem) {
        this.marbleshopSubItems.remove(subItem);
        subItem.setMarbleshopItem(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarbleshopItem that = (MarbleshopItem) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}