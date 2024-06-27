package com.gestone.gestone_api.auth;

import com.gestone.gestone_api.domain.employee.Employee;
import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class ApprovedEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    @OneToOne
    private Employee employee;
    @CreationTimestamp
    private LocalDateTime createdAt;

    private Boolean registered = false;

    private LocalDateTime registrationDate;

    public ApprovedEmployee() {
    }

    public ApprovedEmployee(String email, Employee employee, LocalDateTime createdAt, Boolean registered, LocalDateTime registrationDate) {
        this.email = email;
        this.employee = employee;
        this.createdAt = createdAt;
        this.registered = registered;
        this.registrationDate = registrationDate;
    }

    public Integer getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

}
