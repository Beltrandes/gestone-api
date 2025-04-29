package com.gestone.gestone_api.domain.marbleshop_item;

import com.gestone.gestone_api.domain.material.MarbleshopMaterial;
import com.gestone.gestone_api.domain.order.MarbleshopOrder;
import com.gestone.gestone_api.domain.quotation.Quotation;
import jakarta.persistence.*;

import java.math.BigDecimal;
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
    @OneToOne(orphanRemoval = true)
    private MarbleshopMaterial marbleshopMaterial;
    private BigDecimal unitValue = BigDecimal.ZERO;
    private BigDecimal unitArea = BigDecimal.ZERO;
    private BigDecimal totalValue = BigDecimal.ZERO;
    private BigDecimal totalArea = BigDecimal.ZERO;
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
        this.unitArea = this.measureX.multiply(this.measureY);
        if (this.marbleshopMaterial.getPrice() != null) {
            this.unitValue = this.unitArea.multiply(this.marbleshopMaterial.getPrice());
        }
        marbleshopSubItems.forEach(marbleshopSubItem -> {
            marbleshopSubItem.calculate();
            this.unitValue = this.unitValue.add(marbleshopSubItem.getTotalValue());
            this.unitArea = this.unitArea.add(marbleshopSubItem.getTotalArea());

        });
        this.totalValue = this.unitValue.multiply(BigDecimal.valueOf(this.quantity));
        this.totalArea = this.unitArea.multiply(BigDecimal.valueOf(this.quantity));
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

    public MarbleshopOrder getServiceOrder() {
        return marbleshopOrder;
    }

    public void setServiceOrder(MarbleshopOrder marbleshopOrder) {
        this.marbleshopOrder = marbleshopOrder;
    }
}
