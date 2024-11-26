package com.gestone.gestone_api.domain.marbleshop_item;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

public interface IMarbleshopItemService {

    MarbleshopItem save(MarbleshopItemDTO marbleshopItemDTO);

    MarbleshopItem update(MarbleshopItemDTO marbleshopItemDTO);

    void delete(UUID id);

    MarbleshopItem findById(UUID id);
    List<MarbleshopItem> findByQuotationId(UUID id);

    List<MarbleshopItem> findByMarbleshopOrderId(UUID id);
}
