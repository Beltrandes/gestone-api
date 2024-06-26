package com.gestone.gestone_api.domain.quotation;

import com.gestone.gestone_api.domain.customer.Customer;
import com.gestone.gestone_api.domain.customer.CustomerService;
import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.marbleshop.MarbleshopService;
import com.gestone.gestone_api.domain.material.MarbleshopMaterial;
import com.gestone.gestone_api.domain.material.MarbleshopMaterialService;
import com.gestone.gestone_api.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationService implements IQuotationService {
    @Autowired
    private QuotationRepository quotationRepository;
    @Autowired
    private MarbleshopItemRepository marbleshopItemRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MarbleshopMaterialService marbleshopMaterialService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MarbleshopService marbleshopService;

    @Override
    public Quotation save(QuotationDTO quotationDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Marbleshop marbleshop = tokenService.getMarbleshopFromToken(token);
        Customer customer = customerService.findById(quotationDTO.customerId());
        Quotation quotation = new Quotation();
        quotation.setName(quotationDTO.name());
        quotation.setDetails(quotationDTO.details());
        quotation.setAddress(quotationDTO.address());
        quotation.setDeadlineDays(quotationDTO.deadlineDays());
        quotation.setDaysForDue(quotationDTO.daysForDue());
        quotation.setCustomer(customer);
        quotation.setMarbleshop(marbleshop);
        List<MarbleshopItem> marbleshopItems = quotationDTO.marbleshopItems().stream().map(marbleshopItemDTO -> {
            MarbleshopMaterial marbleshopMaterial = marbleshopMaterialService.findById(marbleshopItemDTO.marbleshopMaterialId());
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
        }).toList();
        quotation.setMarbleshopItems(marbleshopItems);
        quotation.calculate();
        return quotationRepository.save(quotation);

    }

    public Quotation validateDueDate(Quotation quotation) {
        quotation.checkDueDate();
        return quotationRepository.save(quotation);
    }

    public List<Quotation> findAll(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Marbleshop marbleshop = tokenService.getMarbleshopFromToken(token);
        List<Quotation> quotations = marbleshop.getQuotations();
        quotations.forEach(this::validateDueDate);
        marbleshop.setQuotations(quotations);
        marbleshopService.saveMarbleshop(marbleshop);
        return marbleshop.getQuotations();
    }


}
