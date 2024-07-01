package com.gestone.gestone_api.domain.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class ServiceOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private List<ServiceOrderItem> serviceOrderItems = new ArrayList<>();

    private ServiceOrderStatus serviceOrderStatus = ServiceOrderStatus.PENDING;
    @CreationTimestamp
    private LocalDateTime createdAt;


    public ServiceOrderItem() {
    }


}
