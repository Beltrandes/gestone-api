package com.gestone.gestone_api.domain.installation_order;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.order.MarbleshopOrder;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class InstallationOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private MarbleshopOrder order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marbleshop_id", nullable = false)
    private Marbleshop marbleshop;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InstallationOrderStatus status = InstallationOrderStatus.PENDING;

    private String installers;

    private String address;

    @Column(columnDefinition = "TEXT")
    private String notes;

    private LocalDateTime scheduledDate;

    private LocalDateTime completionDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "installation_order_images", joinColumns = @JoinColumn(name = "installation_order_id"))
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

    public InstallationOrder() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MarbleshopOrder getOrder() {
        return order;
    }

    public void setOrder(MarbleshopOrder order) {
        this.order = order;
    }

    public Marbleshop getMarbleshop() {
        return marbleshop;
    }

    public void setMarbleshop(Marbleshop marbleshop) {
        this.marbleshop = marbleshop;
    }

    public InstallationOrderStatus getStatus() {
        return status;
    }

    public void setStatus(InstallationOrderStatus status) {
        this.status = status;
    }

    public String getInstallers() {
        return installers;
    }

    public void setInstallers(String installers) {
        this.installers = installers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
