package com.gestone.gestone_api.domain.employee;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.marbleshop.MarbleshopResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class EmployeeResponseDTO {
        UUID id;
        String name;
        String documentNumber;
        BigDecimal salary;
        String phone;
        String employeeRole;
        MarbleshopResponseDTO marbleshop;
        LocalDateTime createdAt;

    public EmployeeResponseDTO(UUID id, String name, String documentNumber, BigDecimal salary, String phone, String employeeRole, MarbleshopResponseDTO marbleshop, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.documentNumber = documentNumber;
        this.salary = salary;
        this.phone = phone;
        this.employeeRole = employeeRole;
        this.marbleshop = marbleshop;
        this.createdAt = createdAt;
    }

    public static EmployeeResponseDTO fromEmployee(Employee employee) {
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getDocumentNumber(),
                employee.getSalary(),
                employee.getPhone(),
                employee.getEmployeeRole().name(),
                new MarbleshopResponseDTO(employee.getMarbleshop()),
                employee.getCreatedAt()

        );
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

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
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
