package com.gestone.gestone_api.domain.marbleshop;

import com.gestone.gestone_api.domain.customer.Customer;
import com.gestone.gestone_api.domain.employee.Employee;
import com.gestone.gestone_api.domain.marbleshop_material.MarbleshopMaterial;
import com.gestone.gestone_api.domain.miscellaneous_material.MiscellaneousMaterial;
import com.gestone.gestone_api.domain.quotation.Quotation;
import com.gestone.gestone_api.domain.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Marbleshop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String phone;
    @OneToMany(mappedBy = "marbleshop")
    private List<User> users = new ArrayList<>();
    @OneToMany(mappedBy = "marbleshop")
    private List<Employee> employees = new ArrayList<>();
    @OneToMany(mappedBy = "marbleshop")
    private List<Customer> customers = new ArrayList<>();
    @OneToMany(mappedBy = "marbleshop")
    private List<MarbleshopMaterial> marbleshopMaterials = new ArrayList<>();
    @OneToMany(mappedBy = "marbleshop")
    private List<MiscellaneousMaterial> miscellaneousMaterials = new ArrayList<>();
    @OneToMany(mappedBy = "marbleshop")
    private List<Quotation> quotations = new ArrayList<>();

    private String logoPath;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Marbleshop() {
    }

    public Marbleshop(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    public List<User> getUsers() {
        return users;
    }


    public List<Employee> getEmployees() {
        return employees;
    }
    public List<MarbleshopMaterial> getMarbleshopMaterials() {
        return marbleshopMaterials;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Quotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(List<Quotation> quotations) {
        this.quotations = quotations;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void setMarbleshopMaterials(List<MarbleshopMaterial> marbleshopMaterials) {
        this.marbleshopMaterials = marbleshopMaterials;
    }

    public List<MiscellaneousMaterial> getMiscellaneousMaterials() {
        return miscellaneousMaterials;
    }

    public void setMiscellaneousMaterials(List<MiscellaneousMaterial> miscellaneousMaterials) {
        this.miscellaneousMaterials = miscellaneousMaterials;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
