package com.gestone.gestone_api.domain.employee;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String documentNumber;
    private BigDecimal salary;
    private String phone;
    private EmployeeRole employeeRole;
    @ManyToOne(fetch = FetchType.LAZY)
    private Marbleshop marbleshop;
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    public Employee() {
    }

    public Employee(UUID id, String name, String documentNumber, BigDecimal salary, String phone,
            EmployeeRole employeeRole, Marbleshop marbleshop, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.documentNumber = documentNumber;
        this.salary = salary;
        this.phone = phone;
        this.employeeRole = employeeRole;
        this.marbleshop = marbleshop;
        this.createdAt = createdAt;
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

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }

    public Marbleshop getMarbleshop() {
        return marbleshop;
    }

    public void setMarbleshop(Marbleshop marbleshop) {
        this.marbleshop = marbleshop;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    
    
}
