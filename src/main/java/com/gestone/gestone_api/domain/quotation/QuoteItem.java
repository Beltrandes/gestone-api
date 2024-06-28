package com.gestone.gestone_api.domain.quotation;

import com.gestone.gestone_api.domain.material.Material;
import com.gestone.gestone_api.domain.material.MaterialType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class QuoteItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String details;
    private BigDecimal measureX;
    private  BigDecimal measureY;
    private Integer quantity;
    @OneToOne
    private Material material;
    private BigDecimal value;
    private BigDecimal area;
    private BigDecimal totalValue;
    private BigDecimal totalArea;
    @ManyToOne
    private Quotation quotation;

    public void calculate() {
        if (material.getMaterialType() != MaterialType.OTHER) {
            area = measureX.multiply(measureY);
            totalArea = area.multiply(new BigDecimal(quantity));
            value = area.multiply(material.getPrice());
            totalValue = value.multiply(new BigDecimal(quantity));

        }
        if (material.getMaterialType() == MaterialType.OTHER) {
            value = material.getPrice();
            totalValue = material.getPrice().multiply(new BigDecimal(quantity));
        }
    }

}
