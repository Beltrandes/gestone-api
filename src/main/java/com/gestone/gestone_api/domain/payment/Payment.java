package com.gestone.gestone_api.domain.payment;

import com.gestone.gestone_api.domain.order.MarbleshopOrder;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String details;
    private BigDecimal totalValue = BigDecimal.ZERO;
    private BigDecimal payedValue = BigDecimal.ZERO;
    private BigDecimal balanceValue = BigDecimal.ZERO;
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;
    @ManyToOne(fetch = FetchType.LAZY)
    private MarbleshopOrder marbleshopOrder;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Payment() {
    }

    public UUID getId() {
        return id;
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

    public BigDecimal getPayedValue() {
        return payedValue;
    }

    public void setPayedValue(BigDecimal payedValue) {
        this.payedValue = payedValue;
    }

    public BigDecimal getBalanceValue() {
        return balanceValue;
    }

    public void setBalanceValue(BigDecimal balanceValue) {
        this.balanceValue = balanceValue;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public MarbleshopOrder getMarbleshopOrder() {
        return marbleshopOrder;
    }

    public void setMarbleshopOrder(MarbleshopOrder marbleshopOrder) {
        this.marbleshopOrder = marbleshopOrder;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
