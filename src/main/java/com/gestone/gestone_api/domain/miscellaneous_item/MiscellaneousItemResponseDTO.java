package com.gestone.gestone_api.domain.miscellaneous_item;

import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopSubItemResponseDTO;
import com.gestone.gestone_api.domain.material.MiscellaneousMaterial;
import com.gestone.gestone_api.domain.material.MiscellaneousMaterialResponseDTO;
import com.gestone.gestone_api.domain.order.MarbleshopOrder;
import com.gestone.gestone_api.domain.quotation.Quotation;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class MiscellaneousItemResponseDTO {
    private UUID id;
    private String name;
    private String details;
    private Integer quantity;
    private BigDecimal unitValue;
    private BigDecimal totalValue;
    private MiscellaneousMaterialResponseDTO miscellaneousMaterial;

    public MiscellaneousItemResponseDTO(MiscellaneousItem miscellaneousItem) {
        this.id = miscellaneousItem.getId();
        this.name = miscellaneousItem.getName();
        this.details = miscellaneousItem.getDetails();
        this.quantity = miscellaneousItem.getQuantity();
        this.unitValue = miscellaneousItem.getUnitValue();
        this.totalValue = miscellaneousItem.getTotalValue();
        this.miscellaneousMaterial = new MiscellaneousMaterialResponseDTO(miscellaneousItem.getMiscellaneousMaterial());
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

    public MiscellaneousMaterialResponseDTO getMiscellaneousMaterial() {
        return miscellaneousMaterial;
    }

    public void setMiscellaneousMaterial(MiscellaneousMaterialResponseDTO miscellaneousMaterial) {
        this.miscellaneousMaterial = miscellaneousMaterial;
    }
}
