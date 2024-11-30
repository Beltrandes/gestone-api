package com.gestone.gestone_api.domain.customer;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.marbleshop.MarbleshopResponseDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class CustomerResponseDTO {

     UUID id;
     String name;
     String phone;
     String email;
     String address;
     MarbleshopResponseDTO marbleshop;
     LocalDateTime createdAt;

    public CustomerResponseDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.phone = customer.getPhone();
        this.email = customer.getEmail();
        this.address = customer.getAddress();
        this.marbleshop = new MarbleshopResponseDTO(customer.getMarbleshop());
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

    public MarbleshopResponseDTO getMarbleshop() {
        return marbleshop;
    }

    public void setMarbleshop(MarbleshopResponseDTO marbleshop) {
        this.marbleshop = marbleshop;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
