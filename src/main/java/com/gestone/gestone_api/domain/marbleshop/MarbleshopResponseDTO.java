package com.gestone.gestone_api.domain.marbleshop;

import java.time.LocalDateTime;
import java.util.UUID;

public class MarbleshopResponseDTO {
        UUID id;
        String name;
        String email;
        String phone;
        LocalDateTime createdAt;

    public MarbleshopResponseDTO(UUID id, String name, String email, String phone, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public static MarbleshopResponseDTO fromMarbleshop(Marbleshop marbleshop) {
        return new MarbleshopResponseDTO(
                marbleshop.getId(),
                marbleshop.getName(),
                marbleshop.getEmail(),
                marbleshop.getPhone(),
                marbleshop.getCreatedAt()
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}