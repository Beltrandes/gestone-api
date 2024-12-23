package com.gestone.gestone_api.domain.quotation;

import com.gestone.gestone_api.domain.customer.CustomerListResponseDTO;
import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopItemResponseDTO;
import com.gestone.gestone_api.domain.miscellaneous_item.MiscellaneousItemResponseDTO;
import com.gestone.gestone_api.domain.user.UserResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class QuotationResponseDTO {
    UUID id;
    String name;
    String details;
    String address;
    Integer deadlineDays;
    Integer daysForDue;
    BigDecimal totalValue;
    BigDecimal totalArea;
    QuotationStatus quotationStatus;
    CustomerListResponseDTO customer;
    UserResponseDTO user;
    List<MarbleshopItemResponseDTO> marbleshopItems;
    List<MiscellaneousItemResponseDTO> miscellaneousItems;
    LocalDateTime createdAt;

    public QuotationResponseDTO(Quotation quotation) {
        this.id = quotation.getId();
        this.name = quotation.getName();
        this.details = quotation.getDetails();
        this.address = quotation.getAddress();
        this.deadlineDays = quotation.getDeadlineDays();
        this.daysForDue = quotation.getDaysForDue();
        this.totalValue = quotation.getTotalValue();
        this.totalArea = quotation.getTotalArea();
        this.quotationStatus = quotation.getQuotationStatus();
        this.customer = new CustomerListResponseDTO(quotation.getCustomer());
        this.user = new UserResponseDTO(quotation.getUser());
        this.miscellaneousItems = quotation.getMiscellaneousItems().stream().map(MiscellaneousItemResponseDTO::new).toList();
        this.marbleshopItems = quotation.getMarbleshopItems().stream().map(MarbleshopItemResponseDTO::new).toList();
        this.createdAt = quotation.getCreatedAt();
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDeadlineDays() {
        return deadlineDays;
    }

    public void setDeadlineDays(Integer deadlineDays) {
        this.deadlineDays = deadlineDays;
    }

    public Integer getDaysForDue() {
        return daysForDue;
    }

    public void setDaysForDue(Integer daysForDue) {
        this.daysForDue = daysForDue;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(BigDecimal totalArea) {
        this.totalArea = totalArea;
    }

    public QuotationStatus getQuotationStatus() {
        return quotationStatus;
    }

    public void setQuotationStatus(QuotationStatus quotationStatus) {
        this.quotationStatus = quotationStatus;
    }

    public CustomerListResponseDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerListResponseDTO customer) {
        this.customer = customer;
    }

    public List<MarbleshopItemResponseDTO> getMarbleshopItems() {
        return marbleshopItems;
    }

    public void setMarbleshopItems(List<MarbleshopItemResponseDTO> marbleshopItems) {
        this.marbleshopItems = marbleshopItems;
    }

    public List<MiscellaneousItemResponseDTO> getMiscellaneousItems() {
        return miscellaneousItems;
    }

    public void setMiscellaneousItems(List<MiscellaneousItemResponseDTO> miscellaneousItems) {
        this.miscellaneousItems = miscellaneousItems;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
