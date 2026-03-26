package com.gestone.gestone_api.domain.visit;

import com.gestone.gestone_api.domain.customer.Customer;
import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "marbleshop_id", nullable = false)
    private Marbleshop marbleshop;

    @Enumerated(EnumType.STRING)
    private VisitReason reason;

    private LocalDateTime scheduledAt;

    @Column(length = 1000)
    private String notes;

    private boolean completed = false;

    private LocalDateTime completedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Visit() {
    }

    public Visit(Customer customer, Marbleshop marbleshop, VisitReason reason, LocalDateTime scheduledAt, String notes) {
        this.customer = customer;
        this.marbleshop = marbleshop;
        this.reason = reason;
        this.scheduledAt = scheduledAt;
        this.notes = notes;
    }

    public UUID getId() { return id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Marbleshop getMarbleshop() { return marbleshop; }
    public void setMarbleshop(Marbleshop marbleshop) { this.marbleshop = marbleshop; }

    public VisitReason getReason() { return reason; }
    public void setReason(VisitReason reason) { this.reason = reason; }

    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
