package com.gestone.gestone_api.domain.quotation;

import com.gestone.gestone_api.domain.customer.Customer;
import com.gestone.gestone_api.domain.customer.CustomerService;
import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.marbleshop.MarbleshopService;
import com.gestone.gestone_api.domain.marbleshop_item.*;
import com.gestone.gestone_api.domain.marbleshop_material.MarbleshopMaterial;
import com.gestone.gestone_api.domain.marbleshop_material.MarbleshopMaterialService;
import com.gestone.gestone_api.domain.miscellaneous_item.MiscellaneousItem;
import com.gestone.gestone_api.domain.miscellaneous_item.MiscellaneousItemDTO;
import com.gestone.gestone_api.domain.miscellaneous_material.MiscellaneousMaterial;
import com.gestone.gestone_api.domain.miscellaneous_material.MiscellaneousMaterialService;
import com.gestone.gestone_api.domain.user.User;
import com.gestone.gestone_api.domain.user.UserService;
import com.gestone.gestone_api.infra.security.TokenService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
        quotation.setInstallationValue(quotationDTO.installationValue());
        List<MarbleshopItem> marbleshopItems = quotationDTO.marbleshopItems().stream().map(marbleshopItemDTO -> {
            MarbleshopMaterial marbleshopMaterial = marbleshopMaterialService
                    .findById(marbleshopItemDTO.marbleshopMaterialId());
            MarbleshopItem marbleshopItem = new MarbleshopItem();
            marbleshopItem.setName(marbleshopItemDTO.name());
            marbleshopItem.setDescription(marbleshopItemDTO.description());
            marbleshopItem.setMeasureX(marbleshopItemDTO.measureX());
            marbleshopItem.setMeasureY(marbleshopItemDTO.measureY());
            marbleshopItem.setQuantity(marbleshopItemDTO.quantity());
            marbleshopItem.setMarbleshopItemType(marbleshopItemDTO.marbleshopItemType());
            marbleshopItem.setQuotation(quotation);
            marbleshopItem.setMarbleshopMaterial(marbleshopMaterial);
            List<MarbleshopSubItem> marbleshopSubItems = Optional.ofNullable(marbleshopItemDTO.marbleshopSubItems())
                    .orElse(List.of()).stream().map(marbleshopSubItemDTO -> {
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
        List<MiscellaneousItem> miscellaneousItems = quotationDTO.miscellaneousItems().stream()
                .map(miscellaneousItemDTO -> {
                    MiscellaneousMaterial miscellaneousMaterial = miscellaneousMaterialService
                            .findById(miscellaneousItemDTO.miscellaneousMaterialId());
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
        quotation.setLocalId((maxLocalId != null ? maxLocalId : 0) + 1);
        return quotationRepository.save(quotation);

    }

    @Transactional
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
        quotation.setInstallationValue(quotationDTO.installationValue());
        Set<UUID> dtoMarbleshopItemIds = quotationDTO.marbleshopItems().stream().map(MarbleshopItemDTO::id)
                .filter(Objects::nonNull).collect(Collectors.toSet());
        Set<UUID> dtoMiscellaneousItemIds = quotationDTO.miscellaneousItems().stream().map(MiscellaneousItemDTO::id)
                .filter(Objects::nonNull).collect(Collectors.toSet());
        quotation.getMarbleshopItems().removeIf(existingItem -> !dtoMarbleshopItemIds.contains(existingItem.getId()));
        quotation.getMiscellaneousItems().removeIf(
                existingMiscellaneousItem -> !dtoMiscellaneousItemIds.contains(existingMiscellaneousItem.getId()));
        for (var marbleshopItemDTO : quotationDTO.marbleshopItems()) {
            MarbleshopItem itemToPersist;

            if (marbleshopItemDTO.id() != null) {
                itemToPersist = quotation.getMarbleshopItems().stream()
                        .filter(i -> i.getId().equals(marbleshopItemDTO.id())).findFirst()
                        .orElseThrow(() -> new EntityNotFoundException(
                                "MarbleshopItem com ID " + marbleshopItemDTO.id() + " não encontrado."));

                this.syncMarbleshopSubItems(itemToPersist, marbleshopItemDTO.marbleshopSubItems());

            } else {
                itemToPersist = new MarbleshopItem();
                quotation.addMarbleshopItem(itemToPersist);
            }

            MarbleshopMaterial material = marbleshopMaterialService.findById(marbleshopItemDTO.marbleshopMaterialId());

            if (itemToPersist.getMarbleshopMaterial() == null
                    || !itemToPersist.getMarbleshopMaterial().getId().equals(material.getId())
                    || itemToPersist.getMaterialPriceSnapshot() == null) {
                itemToPersist.setMaterialPriceSnapshot(material.getPrice());
            }

            itemToPersist.setName(marbleshopItemDTO.name());
            itemToPersist.setDescription(marbleshopItemDTO.description());
            itemToPersist.setMeasureX(marbleshopItemDTO.measureX());
            itemToPersist.setMeasureY(marbleshopItemDTO.measureY());
            itemToPersist.setQuantity(marbleshopItemDTO.quantity());
            itemToPersist.setMarbleshopItemType(marbleshopItemDTO.marbleshopItemType());
            itemToPersist.setMarbleshopMaterial(material);

            if (marbleshopItemDTO.id() == null) {
                this.syncMarbleshopSubItems(itemToPersist, marbleshopItemDTO.marbleshopSubItems());
            }

            itemToPersist.calculate();
        }

        for (var miscellaneousItemDTO : quotationDTO.miscellaneousItems()) {
            MiscellaneousItem miscellaneousItemToPersist;
            if (miscellaneousItemDTO.id() != null) {
                miscellaneousItemToPersist = quotation.getMiscellaneousItems().stream()
                        .filter(mi -> mi.getId().equals(miscellaneousItemDTO.id()))
                        .findFirst()
                        .orElseThrow(() -> new EntityNotFoundException(
                                "MarbleshopItem com ID " + miscellaneousItemDTO.id() + " não encontrado."));
            } else {
                miscellaneousItemToPersist = new MiscellaneousItem();
                quotation.addMiscellaneousItem(miscellaneousItemToPersist);
            }
            MiscellaneousMaterial miscellaneousMaterial = this.miscellaneousMaterialService
                    .findById(miscellaneousItemDTO.miscellaneousMaterialId());

            if (miscellaneousItemToPersist.getMiscellaneousMaterial() == null
                    || !miscellaneousItemToPersist.getMiscellaneousMaterial().getId()
                            .equals(miscellaneousMaterial.getId())
                    || miscellaneousItemToPersist.getMaterialPriceSnapshot() == null) {
                miscellaneousItemToPersist.setMaterialPriceSnapshot(miscellaneousMaterial.getPrice());
            }

            miscellaneousItemToPersist.setName(miscellaneousItemDTO.name());
            miscellaneousItemToPersist.setQuantity(miscellaneousItemDTO.quantity());
            miscellaneousItemToPersist.setDetails(miscellaneousItemDTO.details());
            miscellaneousItemToPersist.setMiscellaneousMaterial(miscellaneousMaterial);
            miscellaneousItemToPersist.calculate();
        }

        quotation.calculate();

        return quotationRepository.save(quotation);
    }

    private void syncMarbleshopSubItems(MarbleshopItem item, List<MarbleshopSubItemDTO> subItemDTOS) {
        Set<UUID> dtoSubItemIds = subItemDTOS.stream().map(MarbleshopSubItemDTO::id).filter(Objects::nonNull)
                .collect(Collectors.toSet());

        item.getMarbleshopSubItems().removeIf(existingSubItem -> !dtoSubItemIds.contains(existingSubItem.getId()));

        for (var subItemDTO : subItemDTOS) {
            MarbleshopSubItem subItemToPersist;

            if (subItemDTO.id() != null) {
                subItemToPersist = item.getMarbleshopSubItems().stream()
                        .filter(si -> si.getId().equals(subItemDTO.id())).findFirst()
                        .orElseThrow(() -> new EntityNotFoundException(
                                "MarbleshopSubItem com ID " + subItemDTO.id() + " não encontrado."));
            } else {
                subItemToPersist = new MarbleshopSubItem();
                item.addMarbleshopSubItem(subItemToPersist);
            }

            subItemToPersist.setName(subItemDTO.name());
            subItemToPersist.setDescription(subItemDTO.description());
            subItemToPersist.setMeasureX(subItemDTO.measureX());
            subItemToPersist.setMeasureY(subItemDTO.measureY());
            subItemToPersist.setQuantity(subItemDTO.quantity());
            subItemToPersist.setMarbleshopSubItemType(subItemDTO.marbleshopSubItemType());
            subItemToPersist.calculate();
        }
    }

    public Quotation validateDueDate(Quotation quotation) {
        if (quotation.checkDueDate()) {
            return quotationRepository.save(quotation);
        }
        return quotation;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void runExpirationJob() {
        quotationRepository.expireAllPastDue(
                LocalDateTime.now(),
                QuotationStatus.PENDING.name(),
                QuotationStatus.EXPIRED.name());
    }

    public List<Quotation> findAll(UUID marbleshopId) {
        List<Quotation> quotations = quotationRepository.findAllByMarbleshopId(marbleshopId);
        boolean anyChanged = false;
        for (Quotation quotation : quotations) {
            if (quotation.checkDueDate()) {
                anyChanged = true;
            }
        }
        if (anyChanged) {
            quotationRepository.saveAll(quotations);
        }
        return quotations;
    }

    public Quotation findById(UUID id) {
        Quotation quotation = quotationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Quotation not found with id: " + id));
        if (quotation.checkDueDate()) {
            quotationRepository.save(quotation);
        }
        return quotation;
    }

    public Quotation recalculateQuotation(UUID id) {
        Quotation quotation = this.findById(id);
        for (MarbleshopItem item : quotation.getMarbleshopItems())
            item.calculate();
        ;
        for (MiscellaneousItem item : quotation.getMiscellaneousItems())
            item.calculate();
        quotation.calculate();
        return quotationRepository.save(quotation);
    }

    @Transactional
    public void updateQuotationItemPrice(UUID marbleshopItemId, UpdateItemValueRequest request) {
        MarbleshopItem marbleshopItem = marbleshopItemRepository.findById(marbleshopItemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        marbleshopItem.setUnitValue(request.value());
        marbleshopItem.calculateTotalValue();
        Quotation quotation = marbleshopItem.getQuotation();
        quotation.calculate();
        quotationRepository.save(quotation);
    }

}
