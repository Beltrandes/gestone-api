package com.gestone.gestone_api.domain.order;

import com.gestone.gestone_api.domain.customer.Customer;
import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopItem;
import com.gestone.gestone_api.domain.miscellaneous_item.MiscellaneousItem;
import com.gestone.gestone_api.domain.payment.Payment;
import com.gestone.gestone_api.domain.payment.PaymentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class MarbleshopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Integer localId;
    private String workAddress;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    private BigDecimal totalValue = BigDecimal.ZERO;
    private BigDecimal totalArea = BigDecimal.ZERO;

    private BigDecimal discount;
    @Column(precision = 10, scale = 2)
    private BigDecimal finalValue = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalPaid = BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    private MarbleshopOrderStatus marbleshopOrderStatus = MarbleshopOrderStatus.PROJECTING;
    @OneToMany(mappedBy = "marbleshopOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MarbleshopItem> marbleshopItems = new ArrayList<>();
    @OneToMany(mappedBy = "marbleshopOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MiscellaneousItem> miscellaneousItems = new ArrayList<>();
    @OneToMany(mappedBy = "marbleshopOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Marbleshop marbleshop;

    private LocalDateTime estimatedInstallmentDate;

    private LocalDateTime installmentDate;

    private String notes;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public MarbleshopOrder() {
    }

    public void calculate() {
        var marbleshopItemsTotalValue = getMarbleshopItems().stream().map(MarbleshopItem::getTotalValue).reduce(BigDecimal.ZERO, BigDecimal::add);

        var miscellaneousItemsTotalValue = getMiscellaneousItems().stream().map(MiscellaneousItem::getTotalValue).reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalValue = marbleshopItemsTotalValue.add(miscellaneousItemsTotalValue);
        this.totalArea = getMarbleshopItems().stream().map(MarbleshopItem::getTotalArea).reduce(BigDecimal.ZERO, BigDecimal::add);

        this.finalValue = totalValue.subtract(this.discount);

        this.paymentStatus = calculatePaymentStatus();

    }

    public PaymentStatus calculatePaymentStatus() {
        BigDecimal totalPaid = getTotalPaid();


        if (totalPaid.compareTo(this.finalValue) >= 0)
            return PaymentStatus.PAID;

        if (this.installmentDate != null && this.installmentDate.isBefore(LocalDateTime.now()))
            return PaymentStatus.OVERDUE;

        return PaymentStatus.PARTIALLY_PAID;
    }

    public void updatePaymentStatus() {
        this.paymentStatus = calculatePaymentStatus();
    }

    public void addPayment(Payment payment) {
        payment.setMarbleshopOrder(this);
        this.payments.add(payment);
        updatePaymentStatus();
        setTotalPaid();
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

    public Marbleshop getMarbleshop() {
        return marbleshop;
    }

    public void setMarbleshop(Marbleshop marbleshop) {
        this.marbleshop = marbleshop;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public MarbleshopOrderStatus getMarbleshopOrderStatus() {
        return marbleshopOrderStatus;
    }

    public void setMarbleshopOrderStatus(MarbleshopOrderStatus marbleshopOrderStatus) {
        this.marbleshopOrderStatus = marbleshopOrderStatus;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(BigDecimal finalValue) {
        this.finalValue = finalValue;
    }

    public LocalDateTime getEstimatedInstallmentDate() {
        return estimatedInstallmentDate;
    }

    public void setEstimatedInstallmentDate(LocalDateTime estimatedInstallmentDate) {
        this.estimatedInstallmentDate = estimatedInstallmentDate;
    }

    public LocalDateTime getInstallmentDate() {
        return installmentDate;
    }

    public void setInstallmentDate(LocalDateTime installmentDate) {
        this.installmentDate = installmentDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<MiscellaneousItem> getMiscellaneousItems() {
        return miscellaneousItems;
    }

    public void setMiscellaneousItems(List<MiscellaneousItem> miscellaneousItems) {
        this.miscellaneousItems = miscellaneousItems;
    }

    public BigDecimal getTotalPaid() {
        return payments.stream().map(Payment::getPayedValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setTotalPaid() {
        this.totalPaid = getTotalPaid();
    }

    public PaymentStatus getPaymentStatus() {

        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
