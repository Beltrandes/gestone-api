package com.gestone.gestone_api.domain.order;

import com.gestone.gestone_api.domain.marbleshop.MarbleshopService;
import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopItem;
import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopSubItem;
import com.gestone.gestone_api.domain.marbleshop_material.MarbleshopMaterial;
import com.gestone.gestone_api.domain.miscellaneous_item.MiscellaneousItem;
import com.gestone.gestone_api.domain.miscellaneous_material.MiscellaneousMaterial;
import com.gestone.gestone_api.domain.payment.Payment;
import com.gestone.gestone_api.domain.quotation.Quotation;
import com.gestone.gestone_api.domain.quotation.QuotationService;
import com.gestone.gestone_api.domain.quotation.QuotationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MarbleshopOrderService implements IMarbleshopOrderService {

    @Autowired
    MarbleshopOrderRepository marbleshopOrderRepository;
    @Autowired
    QuotationService quotationService;

    @Autowired
    MarbleshopService marbleshopService;

    @Override
    public MarbleshopOrder save(MarbleshopOrderDTO marbleshopOrderDTO) {
        Quotation quotation = quotationService.findById(marbleshopOrderDTO.quotationId());
        MarbleshopOrder order = new MarbleshopOrder();
        List<MarbleshopItem> marbleshopItems = quotation.getMarbleshopItems().stream().map(marbleshopItemDTO -> {
            MarbleshopMaterial marbleshopMaterial = marbleshopItemDTO.getMarbleshopMaterial();
            MarbleshopItem marbleshopItem = new MarbleshopItem();
            marbleshopItem.setName(marbleshopItemDTO.getName());
            marbleshopItem.setDescription(marbleshopItemDTO.getDescription());
            marbleshopItem.setMeasureX(marbleshopItemDTO.getMeasureX());
            marbleshopItem.setMeasureY(marbleshopItemDTO.getMeasureY());
            marbleshopItem.setQuantity(marbleshopItemDTO.getQuantity());
            marbleshopItem.setMarbleshopItemType(marbleshopItemDTO.getMarbleshopItemType());
            marbleshopItem.setMarbleshopMaterial(marbleshopMaterial);
            List<MarbleshopSubItem> marbleshopSubItems = Optional.ofNullable(marbleshopItemDTO.getMarbleshopSubItems()).orElse(List.of()).stream().map(marbleshopSubItemDTO -> {
                MarbleshopSubItem marbleshopSubItem = new MarbleshopSubItem();
                marbleshopSubItem.setName(marbleshopSubItemDTO.getName());
                marbleshopSubItem.setDescription(marbleshopSubItemDTO.getDescription());
                marbleshopSubItem.setMeasureX(marbleshopSubItemDTO.getMeasureX());
                marbleshopSubItem.setMeasureY(marbleshopSubItemDTO.getMeasureY());
                marbleshopSubItem.setQuantity(marbleshopSubItemDTO.getQuantity());
                marbleshopSubItem.setMarbleshopSubItemType(marbleshopSubItemDTO.getMarbleshopSubItemType());
                marbleshopSubItem.setMarbleshopItem(marbleshopItem);
                marbleshopSubItem.calculate();
                return marbleshopSubItem;
            }).toList();
            marbleshopItem.setMarbleshopSubItems(marbleshopSubItems);
            marbleshopItem.calculate();
            marbleshopItem.setMarbleshopOrder(order);
            return marbleshopItem;
        }).toList();
        List<MiscellaneousItem> miscellaneousItems = quotation.getMiscellaneousItems().stream().map(miscellaneousItemDTO -> {
            MiscellaneousMaterial miscellaneousMaterial = miscellaneousItemDTO.getMiscellaneousMaterial();
            MiscellaneousItem miscellaneousItem = new MiscellaneousItem();
            miscellaneousItem.setName(miscellaneousItemDTO.getName());
            miscellaneousItem.setDetails(miscellaneousItemDTO.getDetails());
            miscellaneousItem.setQuantity(miscellaneousItemDTO.getQuantity());
            miscellaneousItem.setMiscellaneousMaterial(miscellaneousMaterial);
            miscellaneousItem.calculate();
            return miscellaneousItem;
        }).toList();
        order.setMiscellaneousItems(miscellaneousItems);
        order.setMarbleshopItems(marbleshopItems);
        order.setDiscount(marbleshopOrderDTO.discount());
        order.setMarbleshop(quotation.getMarbleshop());
        order.setCustomer(quotation.getCustomer());
        order.setWorkAddress(marbleshopOrderDTO.workAddress());
        order.setNotes(marbleshopOrderDTO.notes());
        order.calculate();
        order.setEstimatedInstallmentDate(LocalDateTime.now().plusDays(quotation.getDeadlineDays()));
        Integer maxLocalId = marbleshopOrderRepository.findMaxLocalIdByMarbleshop(quotation.getMarbleshop().getId());
        order.setLocalId((maxLocalId != null ? maxLocalId : 0) + 1);
        marbleshopOrderDTO.payments().forEach(paymentDTO -> {
            Payment payment = new Payment();
            payment.setCustomer(quotation.getCustomer());
            payment.setPayedValue(paymentDTO.payedValue());
            payment.setPaymentType(paymentDTO.paymentType());
            payment.setDetails(paymentDTO.details());
            payment.setMarbleshopOrder(order);
            payment.setMarbleshop(quotation.getMarbleshop());
            order.addPayment(payment);

        });


        order.calculate();


        quotation.setQuotationStatus(QuotationStatus.APPROVED);
        return marbleshopOrderRepository.save(order);
    }

    @Override
    public List<MarbleshopOrder> findAll(UUID marbleshopId) {
        return marbleshopOrderRepository.findByMarbleshopId(marbleshopId);
    }

    @Override
    public MarbleshopOrder findById(UUID id) {
        return marbleshopOrderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));
    }
}
