package com.gestone.gestone_api.domain.marbleshop_order;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopItem;
import com.gestone.gestone_api.domain.miscellaneous_item.MiscellaneousItem;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class MarbleshopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String details;
    private BigDecimal totalValue = BigDecimal.ZERO;
    private BigDecimal totalArea = BigDecimal.ZERO;
    private Integer deadlineDays;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;
    @OneToMany(mappedBy = "marbleshopOrder")
    private List<MarbleshopItem> marbleshopItems;
    @OneToMany(mappedBy = "marbleshopOrder")
    private List<MiscellaneousItem> miscellaneousItems;
    @OneToMany(mappedBy = "marbleshopOrder")
    private List<Payment> payments = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Marbleshop marbleshop;
    @CreationTimestamp
    private LocalDateTime createdAt;


    public MarbleshopOrder() {
    }


    public MarbleshopOrder(String name, String details, Integer deadlineDays,Marbleshop marbleshop) {
        this.name = name;
        this.details = details;
        this.deadlineDays = deadlineDays;
        this.marbleshop = marbleshop;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Integer getDeadlineDays() {
        return deadlineDays;
    }

    public void setDeadlineDays(Integer deadlineDays) {
        this.deadlineDays = deadlineDays;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<MarbleshopItem> getMarbleshopItems() {
        return marbleshopItems;
    }

    public void setMarbleshopItems(List<MarbleshopItem> marbleshopItems) {
        this.marbleshopItems = marbleshopItems;
    }

    public List<MiscellaneousItem> getMiscellaneousItems() {
        return miscellaneousItems;
    }

    public void setMiscellaneousItems(List<MiscellaneousItem> miscellaneousItems) {
        this.miscellaneousItems = miscellaneousItems;
    }


    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Marbleshop getMarbleshop() {
        return marbleshop;
    }

    public void setMarbleshop(Marbleshop marbleshop) {
        this.marbleshop = marbleshop;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
