package com.gestone.gestone_api.domain.quotation;

import com.gestone.gestone_api.domain.customer.Customer;
import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL)
    private List<QuoteItem> quoteItems = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Marbleshop marbleshop;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Quotation() {
    }

    public Quotation(String name, String details, String address, Integer deadlineDays, Integer daysForDue, Customer customer) {
        this.name = name;
        this.details = details;
        this.address = address;
        this.deadlineDays = deadlineDays;
        this.daysForDue = daysForDue;
        this.customer = customer;
    }

    public void checkDueDate() {
        if (QuotationStatus.PENDING.equals(quotationStatus)) {
            if (LocalDateTime.now().isAfter(createdAt.plusDays(daysForDue))) {
                this.quotationStatus = QuotationStatus.EXPIRED;
            }
        }

    }

    public void calculate() {
        this.totalValue = quoteItems.stream().map(QuoteItem::getTotalValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.totalArea = quoteItems.stream().map(QuoteItem::getTotalArea).reduce(BigDecimal.ZERO, BigDecimal::add);
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

    public BigDecimal getTotalArea() {
        return totalArea;
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

    public List<QuoteItem> getQuoteItems() {
        return quoteItems;
    }

    public void setQuoteItems(List<QuoteItem> quoteItems) {
        this.quoteItems = quoteItems;
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


}
