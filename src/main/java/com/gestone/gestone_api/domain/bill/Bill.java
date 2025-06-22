package com.gestone.gestone_api.domain.bill;

import com.gestone.gestone_api.domain.expense.Expense;
import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity

public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    @Column(precision = 10, scale = 2)
    private BigDecimal totalValue = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    private BigDecimal paidValue = BigDecimal.ZERO;

    private LocalDateTime dueDate;
    private LocalDateTime paymentDate;
    @Enumerated(EnumType.STRING)
    private BillStatus status;
    @Enumerated(EnumType.STRING)
    private BillCategory category;
    private String notes;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private Marbleshop marbleshop;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<Expense> expenses = new ArrayList<>();

    public Bill() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getPaidValue() {
        return paidValue;
    }

    public void setPaidValue(BigDecimal paidValue) {
        this.paidValue = paidValue;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate() {
        if (paidValue.compareTo(totalValue) == 0) {
            this.paymentDate = LocalDateTime.now();
        }
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    public BillCategory getCategory() {
        return category;
    }

    public void setCategory(BillCategory category) {
        this.category = category;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateStatus() {
        var today = LocalDateTime.now();

        if (this.paidValue.compareTo(this.totalValue) >= 0) {
            this.status = BillStatus.PAID;
            this.paymentDate = LocalDateTime.now();
            return;
        }


        if (this.dueDate.isBefore(today)) {
            this.status = BillStatus.OVERDUE;

        }


        if (this.paidValue.compareTo(BigDecimal.ZERO) > 0 && this.paidValue.compareTo(this.totalValue) < 0) {
            this.status = BillStatus.PARTIALLY_PAID;
        } else if (this.status != BillStatus.OVERDUE) {
            this.status = BillStatus.PENDING;
        }
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }

    public BigDecimal getTotalPaid() {
        return expenses.stream().map(Expense::getPaidValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setTotalPaid() {
        this.paidValue = getTotalPaid();
    }
}
