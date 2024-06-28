package com.gestone.gestone_api.domain.customer;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.quotation.Quotation;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String phone;
    private String email;
    private String address;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Quotation> quotations = new ArrayList<>();
    @ManyToOne
    private Marbleshop marbleshop;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Customer() {
    }

    public Customer(String name, String phone, String email, String address, Marbleshop marbleshop) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.marbleshop = marbleshop;
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

    public Marbleshop getMarbleshop() {
        return marbleshop;
    }

    public void setMarbleshop(Marbleshop marbleshop) {
        this.marbleshop = marbleshop;
    }

    public List<Quotation> getQuotations() {
        return quotations;
    }
}
