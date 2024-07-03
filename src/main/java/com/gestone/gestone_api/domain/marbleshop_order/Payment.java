package com.gestone.gestone_api.domain.marbleshop_order;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String details;
    private BigDecimal totalValue = BigDecimal.ZERO;
    private BigDecimal payedValue = BigDecimal.ZERO;
    private BigDecimal balanceValue = BigDecimal.ZERO;
    @ManyToOne(fetch = FetchType.LAZY)
    private MarbleshopOrder marbleshopOrder;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
