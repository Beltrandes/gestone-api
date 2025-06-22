package com.gestone.gestone_api.domain.quotation;

import com.gestone.gestone_api.domain.customer.Customer;
import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopItem;
import com.gestone.gestone_api.domain.miscellaneous_item.MiscellaneousItem;
import com.gestone.gestone_api.domain.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Quotation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String details;
    private String address;
    private Integer deadlineDays;
    private Integer daysForDue;
    private BigDecimal totalValue = BigDecimal.ZERO;
    private BigDecimal totalArea = BigDecimal.ZERO;
    private QuotationStatus quotationStatus = QuotationStatus.PENDING;

    @Column(nullable = false)
    private Integer localId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MarbleshopItem> marbleshopItems = new ArrayList<>();
    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MiscellaneousItem> miscellaneousItems = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Marbleshop marbleshop;
    @OneToOne
    private User user;
    @CreationTimestamp
    private LocalDateTime createdAt;

    private String paymentCondition;

    public Quotation() {
    }

    public Quotation(String name, String details, String address, Integer deadlineDays, Integer daysForDue, Customer customer, String paymentCondition) {
        this.name = name;
        this.details = details;
        this.address = address;
        this.deadlineDays = deadlineDays;
        this.daysForDue = daysForDue;
        this.customer = customer;
        this.paymentCondition = paymentCondition;
    }

    public void checkDueDate() {
        if (QuotationStatus.PENDING.equals(quotationStatus)) {
            if (LocalDateTime.now().isAfter(createdAt.plusDays(daysForDue))) {
                this.quotationStatus = QuotationStatus.EXPIRED;
            }
        }

    }

    public void calculate() {
        this.totalValue = marbleshopItems.stream()
                .map(MarbleshopItem::getTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(miscellaneousItems.stream()
                        .map(MiscellaneousItem::getTotalValue)
                        .reduce(BigDecimal.ZERO, BigDecimal::add));
        this.totalValue = this.totalValue.setScale(0, RoundingMode.UP);

        this.totalArea = marbleshopItems.stream()
                .map(MarbleshopItem::getTotalArea)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDeadlineDays() {
        return deadlineDays;
    }

    public void setDeadlineDays(Integer deadlineDays) {
        this.deadlineDays = deadlineDays;
    }

    public Integer getDaysForDue() {
        return daysForDue;
    }

    public void setDaysForDue(Integer daysForDue) {
        this.daysForDue = daysForDue;
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

    public QuotationStatus getQuotationStatus() {
        return quotationStatus;
    }

    public void setQuotationStatus(QuotationStatus quotationStatus) {
        this.quotationStatus = quotationStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    public String getPaymentCondition() {
        return paymentCondition;
    }

    public void setPaymentCondition(String paymentCondition) {
        this.paymentCondition = paymentCondition;
    }
}
