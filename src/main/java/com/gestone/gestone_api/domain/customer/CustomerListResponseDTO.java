package com.gestone.gestone_api.domain.customer;

import com.gestone.gestone_api.domain.marbleshop.MarbleshopResponseDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class CustomerListResponseDTO {
    private UUID id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private LocalDateTime createdAt;

    public CustomerListResponseDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.phone = customer.getPhone();
        this.email = customer.getEmail();
        this.address = customer.getAddress();
        this.createdAt = customer.getCreatedAt();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

