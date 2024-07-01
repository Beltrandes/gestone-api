package com.gestone.gestone_api.domain.quotation;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class MiscellaneousItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String details;
    private BigDecimal value = BigDecimal.ZERO;
    private BigDecimal totalValue = BigDecimal.ZERO;
    private MiscellaneousType miscellaneousType;
    @ManyToOne
    private MarbleshopItem marbleshopItem;
}
