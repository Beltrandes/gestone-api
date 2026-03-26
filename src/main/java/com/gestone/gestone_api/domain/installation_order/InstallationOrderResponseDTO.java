package com.gestone.gestone_api.domain.installation_order;

import com.gestone.gestone_api.domain.order.MarbleshopOrderResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class InstallationOrderResponseDTO {
    private UUID id;
    private MarbleshopOrderResponseDTO order;
    private InstallationOrderStatus status;
    private String installers;
    private String address;
    private String notes;
    private LocalDateTime scheduledDate;
    private LocalDateTime completionDate;
    private LocalDateTime createdAt;
    private List<String> images;

    public InstallationOrderResponseDTO(InstallationOrder installationOrder) {
        this.id = installationOrder.getId();
        this.order = new MarbleshopOrderResponseDTO(installationOrder.getOrder());
        this.status = installationOrder.getStatus();
        this.installers = installationOrder.getInstallers();
        this.address = installationOrder.getAddress();
        this.notes = installationOrder.getNotes();
        this.scheduledDate = installationOrder.getScheduledDate();
        this.completionDate = installationOrder.getCompletionDate();
        this.createdAt = installationOrder.getCreatedAt();
        this.images = installationOrder.getImages();
    }

    public UUID getId() {
        return id;
    }

    public MarbleshopOrderResponseDTO getOrder() {
        return order;
    }

    public InstallationOrderStatus getStatus() {
        return status;
    }

    public String getInstallers() {
        return installers;
    }

    public String getAddress() {
        return address;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<String> getImages() {
        return images;
    }
}
