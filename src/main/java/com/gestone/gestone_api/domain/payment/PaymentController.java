package com.gestone.gestone_api.domain.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> savePayment(@RequestBody PaymentDTO paymentDTO) {
        Payment payment = paymentService.save(paymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PaymentResponseDTO(payment));
    }

    @GetMapping("/{marbleshopId}")
    public ResponseEntity<List<PaymentResponseDTO>> listPayments(@PathVariable UUID marbleshopId) {
        return ResponseEntity.ok().body(paymentService.findAll(marbleshopId).stream().map(PaymentResponseDTO::new).toList());
    }
}
