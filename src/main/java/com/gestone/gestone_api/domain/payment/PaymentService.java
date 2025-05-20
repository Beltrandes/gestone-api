package com.gestone.gestone_api.domain.payment;

import com.gestone.gestone_api.domain.customer.CustomerService;
import com.gestone.gestone_api.domain.order.MarbleshopOrder;
import com.gestone.gestone_api.domain.order.MarbleshopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
        MarbleshopOrder order = marbleshopOrderService.findById(paymentDTO.marbleshopOrderId());
        payment.setCustomer(order.getCustomer());
        payment.setDetails(paymentDTO.details());
        payment.setPaymentType(paymentDTO.paymentType());
        payment.setPayedValue(paymentDTO.payedValue());
        order.addPayment(payment);
        order.calculatePaymentStatus();

        return paymentRepository.save(payment);
    }
}
