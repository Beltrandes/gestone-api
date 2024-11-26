package com.gestone.gestone_api.domain.customer;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> save(@RequestBody CustomerDTO customerDTO, HttpServletRequest request) {
        var savedCustomer = customerService.save(customerDTO, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerResponseDTO(savedCustomer));
    }

    @GetMapping("/{marbleshopId}")
    public ResponseEntity<List<CustomerListResponseDTO>> findAllCustomers(@PathVariable UUID marbleshopId) {
        var customers = customerService.findAll(marbleshopId).stream().map(customer -> new CustomerListResponseDTO(customer)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }
}
