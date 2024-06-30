package com.gestone.gestone_api.domain.quotation;

import com.gestone.gestone_api.domain.customer.Customer;
import com.gestone.gestone_api.domain.customer.CustomerService;
import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.material.Material;
import com.gestone.gestone_api.domain.material.MaterialService;
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
    private QuoteItemRepository quoteItemRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private TokenService tokenService;

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
        List<QuoteItem> quoteItems = quotationDTO.quoteItems().stream().map(quoteItemDTO -> {
            Material material = materialService.findById(quoteItemDTO.materialId());
            QuoteItem quoteItem = new QuoteItem();
            quoteItem.setName(quoteItemDTO.name());
            quoteItem.setDetails(quoteItemDTO.details());
            quoteItem.setMeasureX(quoteItemDTO.measureX());
            quoteItem.setMeasureY(quoteItemDTO.measureY());
            quoteItem.setQuantity(quoteItemDTO.quantity());
            quoteItem.setQuotation(quotation);
            quoteItem.setMaterial(material);
            quoteItem.calculate();
            return quoteItem;
        }).toList();
        quotation.setQuoteItems(quoteItems);
        quotation.calculate();
        return quotationRepository.save(quotation);

    }


}
