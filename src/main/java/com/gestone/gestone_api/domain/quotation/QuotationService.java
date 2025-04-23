package com.gestone.gestone_api.domain.quotation;

import com.gestone.gestone_api.domain.customer.Customer;
import com.gestone.gestone_api.domain.customer.CustomerService;
import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.marbleshop.MarbleshopService;
import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopItem;
import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopItemRepository;
import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopSubItem;
import com.gestone.gestone_api.domain.material.MarbleshopMaterial;
import com.gestone.gestone_api.domain.material.MarbleshopMaterialService;
import com.gestone.gestone_api.domain.material.MiscellaneousMaterial;
import com.gestone.gestone_api.domain.material.MiscellaneousMaterialService;
import com.gestone.gestone_api.domain.miscellaneous_item.MiscellaneousItem;
import com.gestone.gestone_api.domain.user.User;
import com.gestone.gestone_api.domain.user.UserService;
import com.gestone.gestone_api.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    private MiscellaneousMaterialService miscellaneousMaterialService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MarbleshopService marbleshopService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuotationPdfService pdfService;

    @Override
    public Quotation save(QuotationDTO quotationDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Marbleshop marbleshop = tokenService.getMarbleshopFromToken(token);
        Customer customer = customerService.findById(quotationDTO.customerId());
        User user = userService.findByEmail(quotationDTO.userEmail());
        Quotation quotation = new Quotation();
        quotation.setName(quotationDTO.name());
        quotation.setUser(user);
        quotation.setDetails(quotationDTO.details());
        quotation.setAddress(quotationDTO.address());
        quotation.setDeadlineDays(quotationDTO.deadlineDays());
        quotation.setDaysForDue(quotationDTO.daysForDue());
        quotation.setCustomer(customer);
        quotation.setMarbleshop(marbleshop);
        quotation.setPaymentCondition(quotationDTO.paymentCondition());
        List<MarbleshopItem> marbleshopItems = quotationDTO.marbleshopItems().stream().map(marbleshopItemDTO -> {
            MarbleshopMaterial marbleshopMaterial = marbleshopMaterialService.findById(marbleshopItemDTO.marbleshopMaterialId());
            MarbleshopItem marbleshopItem = new MarbleshopItem();
            marbleshopItem.setName(marbleshopItemDTO.name());
            marbleshopItem.setDescription(marbleshopItemDTO.description());
            marbleshopItem.setMeasureX(marbleshopItemDTO.measureX());
            marbleshopItem.setMeasureY(marbleshopItemDTO.measureY());
            marbleshopItem.setQuantity(marbleshopItemDTO.quantity());
            marbleshopItem.setMarbleshopItemType(marbleshopItemDTO.marbleshopItemType());
            marbleshopItem.setQuotation(quotation);
            marbleshopItem.setMarbleshopMaterial(marbleshopMaterial);
            List<MarbleshopSubItem> marbleshopSubItems = Optional.ofNullable(marbleshopItemDTO.marbleshopSubItems()).orElse(List.of()).stream().map(marbleshopSubItemDTO -> {
                MarbleshopSubItem marbleshopSubItem = new MarbleshopSubItem();
                marbleshopSubItem.setName(marbleshopSubItemDTO.name());
                marbleshopSubItem.setDescription(marbleshopSubItemDTO.description());
                marbleshopSubItem.setMeasureX(marbleshopSubItemDTO.measureX());
                marbleshopSubItem.setMeasureY(marbleshopSubItemDTO.measureY());
                marbleshopSubItem.setQuantity(marbleshopSubItemDTO.quantity());
                marbleshopSubItem.setMarbleshopSubItemType(marbleshopSubItemDTO.marbleshopSubItemType());
                marbleshopSubItem.setMarbleshopItem(marbleshopItem);
                marbleshopSubItem.calculate();
                return marbleshopSubItem;
            }).toList();

            marbleshopItem.setMarbleshopSubItems(marbleshopSubItems);
            marbleshopItem.calculate();
            return marbleshopItem;
        }).toList();
        List<MiscellaneousItem> miscellaneousItems = quotationDTO.miscellaneousItems().stream().map(miscellaneousItemDTO -> {
            MiscellaneousMaterial miscellaneousMaterial = miscellaneousMaterialService.findById(miscellaneousItemDTO.miscellaneousMaterialId());
            MiscellaneousItem miscellaneousItem = new MiscellaneousItem();
            miscellaneousItem.setName(miscellaneousItemDTO.name());
            miscellaneousItem.setDetails(miscellaneousItemDTO.details());
            miscellaneousItem.setQuantity(miscellaneousItemDTO.quantity());
            miscellaneousItem.setMiscellaneousMaterial(miscellaneousMaterial);
            miscellaneousItem.setQuotation(quotation);
            miscellaneousItem.calculate();
            return miscellaneousItem;
        }).toList();
        quotation.setMiscellaneousItems(miscellaneousItems);
        quotation.setMarbleshopItems(marbleshopItems);
        quotation.calculate();
        Integer maxLocalId = quotationRepository.findMaxLocalIdByMarbleshop(marbleshop.getId());
        quotation.setLocalId((maxLocalId != null ? maxLocalId: 0) + 1);
        return quotationRepository.save(quotation);

    }

    public Quotation update(UUID quotationId, QuotationDTO quotationDTO) {
        var quotation = this.findById(quotationId);
        var customer = this.customerService.findById(quotationDTO.customerId());
        var user = this.userService.findByEmail(quotationDTO.userEmail());

        quotation.setName(quotationDTO.name());
        quotation.setUser(user);
        quotation.setDetails(quotationDTO.details());
        quotation.setAddress(quotationDTO.address());
        quotation.setDeadlineDays(quotationDTO.deadlineDays());
        quotation.setDaysForDue(quotationDTO.daysForDue());
        quotation.setCustomer(customer);
        quotation.setPaymentCondition(quotationDTO.paymentCondition());

        // --- LIMPA ITENS EXISTENTES DE FORMA SEGURA ---
        quotation.getMarbleshopItems().clear();
        quotation.getMiscellaneousItems().clear();

        // --- ADICIONA NOVOS MARBLESHOP ITEMS ---
        for (var marbleshopItemDTO : quotationDTO.marbleshopItems()) {
            MarbleshopMaterial marbleshopMaterial = marbleshopMaterialService.findById(marbleshopItemDTO.marbleshopMaterialId());
            MarbleshopItem marbleshopItem = new MarbleshopItem();

            marbleshopItem.setName(marbleshopItemDTO.name());
            marbleshopItem.setDescription(marbleshopItemDTO.description());
            marbleshopItem.setMeasureX(marbleshopItemDTO.measureX());
            marbleshopItem.setMeasureY(marbleshopItemDTO.measureY());
            marbleshopItem.setQuantity(marbleshopItemDTO.quantity());
            marbleshopItem.setMarbleshopItemType(marbleshopItemDTO.marbleshopItemType());
            marbleshopItem.setMarbleshopMaterial(marbleshopMaterial);
            marbleshopItem.setQuotation(quotation);

            List<MarbleshopSubItem> marbleshopSubItems = marbleshopItemDTO.marbleshopSubItems().stream().map(marbleshopSubItemDTO -> {
                MarbleshopSubItem subItem = new MarbleshopSubItem();
                subItem.setName(marbleshopSubItemDTO.name());
                subItem.setDescription(marbleshopSubItemDTO.description());
                subItem.setMeasureX(marbleshopSubItemDTO.measureX());
                subItem.setMeasureY(marbleshopSubItemDTO.measureY());
                subItem.setQuantity(marbleshopSubItemDTO.quantity());
                subItem.setMarbleshopSubItemType(marbleshopSubItemDTO.marbleshopSubItemType());
                subItem.setMarbleshopItem(marbleshopItem);
                subItem.calculate();
                return subItem;
            }).toList();

            marbleshopItem.setMarbleshopSubItems(new ArrayList<>(marbleshopSubItems));
            marbleshopItem.calculate();
            quotation.getMarbleshopItems().add(marbleshopItem);
        }

        for (var miscellaneousItemDTO : quotationDTO.miscellaneousItems()) {
            MiscellaneousMaterial material = miscellaneousMaterialService.findById(miscellaneousItemDTO.miscellaneousMaterialId());
            MiscellaneousItem item = new MiscellaneousItem();
            item.setName(miscellaneousItemDTO.name());
            item.setDetails(miscellaneousItemDTO.details());
            item.setQuantity(miscellaneousItemDTO.quantity());
            item.setMiscellaneousMaterial(material);
            item.setQuotation(quotation);
            item.calculate();
            quotation.getMiscellaneousItems().add(item);
        }

        quotation.calculate();

        return quotationRepository.save(quotation);
    }


    public Quotation validateDueDate(Quotation quotation) {
        quotation.checkDueDate();
        return quotationRepository.save(quotation);
    }

    public List<Quotation> findAll(UUID marbleshopId) {
        return quotationRepository.findAllByMarbleshopId(marbleshopId);
    }


    public Quotation findById(UUID id) {
        return quotationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Quotation not found with id: " + id));
    }

}
