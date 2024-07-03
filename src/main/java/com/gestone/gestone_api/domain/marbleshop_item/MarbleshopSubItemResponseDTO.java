package com.gestone.gestone_api.domain.marbleshop_item;

import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopSubItem;

import java.math.BigDecimal;
import java.util.UUID;

public class MarbleshopSubItemResponseDTO {

    UUID id;
    BigDecimal measureX;
    BigDecimal measureY;
    BigDecimal value;
    BigDecimal area;

    public MarbleshopSubItemResponseDTO(MarbleshopSubItem marbleshopSubItem) {
        this.id = marbleshopSubItem.getId();
        this.measureX = marbleshopSubItem.getMeasureX();
        this.measureY = marbleshopSubItem.getMeasureY();
        this.value = marbleshopSubItem.getValue();
        this.area = marbleshopSubItem.getArea();
    }

}
