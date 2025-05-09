package com.gestone.gestone_api.domain.payment;

import com.gestone.gestone_api.domain.customer.CustomerService;
import com.gestone.gestone_api.domain.order.MarbleshopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MarbleshopOrderService marbleshopOrderService;

    public Payment save(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setCustomer(customerService.findById(paymentDTO.customerId()));
        payment.setDetails(paymentDTO.details());
        if (paymentDTO.marbleshopOrderId() != null && paymentDTO.marbleshopOrderId() != UUID.fromString("")) {
        }
        return payment;
    }
}
