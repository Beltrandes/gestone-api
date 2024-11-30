package com.gestone.gestone_api.domain.customer;

import com.gestone.gestone_api.infra.security.TokenService;
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
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> save(@RequestBody CustomerDTO customerDTO, HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        var marbleshop = tokenService.getMarbleshopFromToken(token);
        var customer = new Customer(customerDTO.name(), customerDTO.phone(), customerDTO.email(), customerDTO.address(), marbleshop);
        var savedCustomer = customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerResponseDTO(savedCustomer));
    }

    @GetMapping("/{marbleshopId}")
    public ResponseEntity<List<CustomerListResponseDTO>> findAllCustomers(@PathVariable UUID marbleshopId) {
        var customers = customerService.findAll(marbleshopId).stream().map(customer -> new CustomerListResponseDTO(customer)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable UUID customerId, HttpServletRequest request) {
        var customer = new Customer(customerDTO.name(), customerDTO.phone(), customerDTO.email(), customerDTO.address());
        var updatedCustomer = customerService.update(customer, customerId);
       return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseDTO(updatedCustomer));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID customerId) {
        customerService.delete(customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
