package com.gestone.gestone_api.domain.miscellaneous_material;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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
    @CreationTimestamp
    private LocalDateTime createdAt;

    private boolean active = true;
    @ManyToOne(fetch = FetchType.LAZY)
    private Marbleshop marbleshop;

    public MiscellaneousMaterial() {
    }

    public MiscellaneousMaterial(String name, String details, BigDecimal price, MiscellaneousMaterialType miscellaneousMaterialType) {
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


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Marbleshop getMarbleshop() {
        return marbleshop;
    }

    public void setMarbleshop(Marbleshop marbleshop) {
        this.marbleshop = marbleshop;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
