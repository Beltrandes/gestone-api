package com.gestone.gestone_api.domain.marbleshop_item;

import com.gestone.gestone_api.domain.material.MarbleshopMaterial;
import com.gestone.gestone_api.domain.material.MarbleshopMaterialService;
import com.gestone.gestone_api.domain.quotation.Quotation;
import com.gestone.gestone_api.domain.quotation.QuotationService;
import com.gestone.gestone_api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MarbleshopItemService implements IMarbleshopItemService {
    @Autowired
    private MarbleshopItemRepository marbleshopItemRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MarbleshopMaterialService marbleshopMaterialService;
    @Autowired
    private QuotationService quotationService;


    @Override
    public MarbleshopItem save(MarbleshopItemDTO marbleshopItemDTO) {
        MarbleshopMaterial marbleshopMaterial = marbleshopMaterialService.findById(marbleshopItemDTO.marbleshopMaterialId());
        Quotation quotation = quotationService.findById(marbleshopItemDTO.quotationId());
        MarbleshopItem marbleshopItem = getMarbleshopItem(marbleshopItemDTO, quotation, marbleshopMaterial);
        return marbleshopItemRepository.save(marbleshopItem);
    }

    private static MarbleshopItem getMarbleshopItem(MarbleshopItemDTO marbleshopItemDTO, Quotation quotation, MarbleshopMaterial marbleshopMaterial) {
        MarbleshopItem marbleshopItem = new MarbleshopItem();
        marbleshopItem.setName(marbleshopItemDTO.name());
        marbleshopItem.setDescription(marbleshopItemDTO.description());
        marbleshopItem.setMeasureX(marbleshopItemDTO.measureX());
        marbleshopItem.setMeasureY(marbleshopItemDTO.measureY());
        marbleshopItem.setQuantity(marbleshopItemDTO.quantity());
        marbleshopItem.setQuotation(quotation);
        marbleshopItem.setMarbleshopMaterial(marbleshopMaterial);
        marbleshopItem.calculate();
        return marbleshopItem;
    }

    @Override
    public MarbleshopItem update(MarbleshopItemDTO marbleshopItemDTO) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public MarbleshopItem findById(UUID id) {
        return marbleshopItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Marbleshop item not found with id: " + id));
    }

    @Override
    public List<MarbleshopItem> findByQuotationId(UUID id) {
        return null;
    }

    @Override
    public List<MarbleshopItem> findByMarbleshopOrderId(UUID id) {
        return null;
    }
}
