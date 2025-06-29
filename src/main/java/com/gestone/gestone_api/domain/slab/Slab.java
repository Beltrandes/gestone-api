package com.gestone.gestone_api.domain.slab;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.marbleshop_material.MarbleshopMaterial;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Slab {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    private MarbleshopMaterial material;
    private BigDecimal densityMeasure = BigDecimal.valueOf(0.02);
    private BigDecimal measureX;
    private BigDecimal measureY;
    private BigDecimal area;
    @Enumerated(EnumType.STRING)
    private SlabQuality quality;

    private String notes;
    @Enumerated(EnumType.STRING)
    private SlabStatus status;
    private LocalDate entryDate;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "slab", orphanRemoval = true)
    private List<SlabMovement> slabMovements;

    @ManyToOne(fetch = FetchType.LAZY)
    private Marbleshop marbleshop;



    public Slab() {
    }

    public MarbleshopMaterial getMaterial() {
        return material;
    }

    public void setMaterial(MarbleshopMaterial material) {
        this.material = material;
    }

    public BigDecimal getDensityMeasure() {
        return densityMeasure;
    }

    public void setDensityMeasure(BigDecimal densityMeasure) {
        this.densityMeasure = densityMeasure;
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

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public SlabQuality getQuality() {
        return quality;
    }

    public void setQuality(SlabQuality quality) {
        this.quality = quality;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public SlabStatus getStatus() {
        return status;
    }

    public void setStatus(SlabStatus status) {
        this.status = status;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<SlabMovement> getSlabMovements() {
        return slabMovements;
    }

    public void setSlabMovements(List<SlabMovement> slabMovements) {
        this.slabMovements = slabMovements;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Marbleshop getMarbleshop() {
        return marbleshop;
    }

    public void setMarbleshop(Marbleshop marbleshop) {
        this.marbleshop = marbleshop;
    }

    public void calculateArea() {
        this.area = this.measureX.multiply(this.measureY);
    }

    public UUID getId() {
        return id;
    }
}
