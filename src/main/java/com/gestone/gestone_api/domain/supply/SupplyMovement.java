package com.gestone.gestone_api.domain.supply;

import com.gestone.gestone_api.domain.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class SupplyMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supply_id", nullable = false)
    private Supply supply;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SupplyMovementType type; // IN or OUT

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SupplyMovementCategory category; // PURCHASE, PRODUCTION_USE, etc.

    @Column(nullable = false)
    private Integer quantity;

    @Column(length = 1000)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userAuth; // User who registered the movement

    @CreationTimestamp
    private LocalDateTime createdAt;

    public SupplyMovement() {
    }

    public SupplyMovement(Supply supply, SupplyMovementType type, SupplyMovementCategory category, Integer quantity, String notes, User userAuth) {
        this.supply = supply;
        this.type = type;
        this.category = category;
        this.quantity = quantity;
        this.notes = notes;
        this.userAuth = userAuth;
    }

    public UUID getId() { return id; }

    public Supply getSupply() { return supply; }
    public void setSupply(Supply supply) { this.supply = supply; }

    public SupplyMovementType getType() { return type; }
    public void setType(SupplyMovementType type) { this.type = type; }

    public SupplyMovementCategory getCategory() { return category; }
    public void setCategory(SupplyMovementCategory category) { this.category = category; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public User getUserAuth() { return userAuth; }
    public void setUserAuth(User userAuth) { this.userAuth = userAuth; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
