package com.gestone.gestone_api.domain.order;

import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopItemResponseDTO;
import com.gestone.gestone_api.domain.miscellaneous_item.MiscellaneousItemResponseDTO;
import com.gestone.gestone_api.domain.payment.PaymentResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record MarbleshopOrderResponseDTO(
    UUID id,
    Integer localId,
    String workAddress,
    String customerName,
    BigDecimal totalValue,
    BigDecimal totalArea,
    Integer discount,
    BigDecimal finalValue,
    String marbleshopOrderStatus,
    List<MarbleshopItemResponseDTO> marbleshopItems,
    List<MiscellaneousItemResponseDTO> miscellaneousItems,
    List<PaymentResponseDTO> payments,
    String marbleshopName,
    LocalDateTime estimatedInstallmentDate,
    LocalDateTime installmentDate,
    String notes,
    String paymentStatus,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public MarbleshopOrderResponseDTO(MarbleshopOrder order) {
        this(
                order.getId(),
                order.getLocalId(),
                order.getWorkAddress(),
                order.getCustomer() != null ? order.getCustomer().getName() : null,
                order.getTotalValue(),
                order.getTotalArea(),
                order.getDiscount(),
                order.getFinalValue(),
                order.getMarbleshopOrderStatus() != null ? order.getMarbleshopOrderStatus().name() : null,
                order.getMarbleshopItems() != null ? order.getMarbleshopItems().stream()
                        .map(MarbleshopItemResponseDTO::new)
                        .toList() : List.of(),
                order.getMiscellaneousItems() != null ? order.getMiscellaneousItems().stream()
                        .map(MiscellaneousItemResponseDTO::new)
                        .toList() : List.of(),
                order.getPayments() != null ? order.getPayments().stream()
                        .map(PaymentResponseDTO::new)
                        .toList() : List.of(),
                order.getMarbleshop() != null ? order.getMarbleshop().getName() : null,
                order.getEstimatedInstallmentDate(),
                order.getInstallmentDate(),
                order.getNotes(),
                order.getPaymentStatus() != null ? order.getPaymentStatus().name() : null,
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }
}
