package com.gestone.gestone_api.domain.order;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.payment.Payment;
import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopItem;
import com.gestone.gestone_api.domain.miscellaneous_item.MiscellaneousItem;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class MarbleshopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private BigDecimal totalValue = BigDecimal.ZERO;
    private BigDecimal totalArea = BigDecimal.ZERO;
    private MarbleshopOrderStatus marbleshopOrderStatus = MarbleshopOrderStatus.PENDING;
    @OneToMany(mappedBy = "marbleshopOrder", cascade = CascadeType.ALL)
    private List<MarbleshopItem> marbleshopItems = new ArrayList<>();
    @OneToMany(mappedBy = "marbleshopOrder", cascade = CascadeType.ALL)
    private List<MiscellaneousItem> miscellaneousItems = new ArrayList<>();
    @OneToMany(mappedBy = "marbleshopOrder", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Marbleshop marbleshop;

    public MarbleshopOrder() {
    }

    public void calculate() {
        this.totalValue = marbleshopItems.stream().map(MarbleshopItem::getTotalValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.totalValue = this.totalValue.add(miscellaneousItems.stream().map(MiscellaneousItem::getTotalValue).reduce(BigDecimal.ZERO, BigDecimal::add));
        this.totalArea = marbleshopItems.stream().map(MarbleshopItem::getTotalArea).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public UUID getId() {
        return id;
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

    public MarbleshopOrderStatus getServiceOrderStatus() {
        return marbleshopOrderStatus;
    }

    public void setServiceOrderStatus(MarbleshopOrderStatus marbleshopOrderStatus) {
        this.marbleshopOrderStatus = marbleshopOrderStatus;
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

    public Marbleshop getMarbleshop() {
        return marbleshop;
    }

    public void setMarbleshop(Marbleshop marbleshop) {
        this.marbleshop = marbleshop;
    }
}
