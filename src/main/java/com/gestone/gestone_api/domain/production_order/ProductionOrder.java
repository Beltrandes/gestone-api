package com.gestone.gestone_api.domain.production_order;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.order.MarbleshopOrder;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class ProductionOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    private MarbleshopOrder order;

    private LocalDate expectedStartDate;
    private LocalDate realStartDate;

    private LocalDate expectedEndDate;

    private LocalDate realEndDate;
    @Enumerated(EnumType.STRING)
    private ProductionStatus productionStatus = ProductionStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private ProductionPriority productionPriority;

    private String projectUrl;
    private String notes;
    @OneToMany(mappedBy = "productionOrder", cascade = CascadeType.ALL)
    private List<ProductionOrderItem> productionOrderItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Marbleshop marbleshop;
    public ProductionOrder() {
    }

    public MarbleshopOrder getOrder() {
        return order;
    }

    public void setOrder(MarbleshopOrder order) {
        this.order = order;
    }

    public LocalDate getExpectedStartDate() {
        return expectedStartDate;
    }

    public void setExpectedStartDate(LocalDate expectedStartDate) {
        this.expectedStartDate = expectedStartDate;
    }

    public LocalDate getRealStartDate() {
        return realStartDate;
    }

    public void setRealStartDate(LocalDate realStartDate) {
        this.realStartDate = realStartDate;
    }

    public LocalDate getExpectedEndDate() {
        return expectedEndDate;
    }

    public void setExpectedEndDate(LocalDate expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

    public LocalDate getRealEndDate() {
        return realEndDate;
    }

    public void setRealEndDate(LocalDate realEndDate) {
        this.realEndDate = realEndDate;
    }

    public ProductionStatus getProductionStatus() {
        return productionStatus;
    }

    public void setProductionStatus(ProductionStatus productionStatus) {
        this.productionStatus = productionStatus;
    }

    public ProductionPriority getProductionPriority() {
        return productionPriority;
    }

    public void setProductionPriority(ProductionPriority productionPriority) {
        this.productionPriority = productionPriority;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<ProductionOrderItem> getProductionOrderItems() {
        return productionOrderItems;
    }

    public void setProductionOrderItems(List<ProductionOrderItem> productionOrderItems) {
        this.productionOrderItems = productionOrderItems;
    }

    public UUID getId() {
        return id;
    }

    public Marbleshop getMarbleshop() {
        return marbleshop;
    }

    public void setMarbleshop(Marbleshop marbleshop) {
        this.marbleshop = marbleshop;
    }
}
