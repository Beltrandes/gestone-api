package com.gestone.gestone_api.domain.quotation;

import com.gestone.gestone_api.domain.material.Material;
import com.gestone.gestone_api.domain.material.MaterialType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class QuoteItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String details;
    private BigDecimal measureX = BigDecimal.ZERO;
    private  BigDecimal measureY = BigDecimal.ZERO;
    private Integer quantity;
    @OneToOne
    private Material material;
    private BigDecimal value = BigDecimal.ZERO;
    private BigDecimal area = BigDecimal.ZERO;
    private BigDecimal totalValue = BigDecimal.ZERO;
    private BigDecimal totalArea =  BigDecimal.ZERO;
    @ManyToOne
    private Quotation quotation;

    public QuoteItem() {
    }

    public QuoteItem(String name, String details, BigDecimal measureX, BigDecimal measureY, Integer quantity, Material material) {
        this.name = name;
        this.details = details;
        this.measureX = measureX;
        this.measureY = measureY;
        this.quantity = quantity;
        this.material = material;
    }

    public void calculate() {
        if (material.getMaterialType() != MaterialType.OTHER) {
            area = measureX.multiply(measureY);
            totalArea = area.multiply(new BigDecimal(quantity));
            value = area.multiply(material.getPrice());
            totalValue = value.multiply(new BigDecimal(quantity));

        }
        if (material.getMaterialType() == MaterialType.OTHER) {
            value = material.getPrice();
            totalValue = material.getPrice().multiply(new BigDecimal(quantity));
        }
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
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

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }
}
