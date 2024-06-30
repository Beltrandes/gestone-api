package com.gestone.gestone_api.domain.customer;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TokenService tokenService;

    @Override
    public Customer save(CustomerDTO customerDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Marbleshop marbleshop = tokenService.getMarbleshopFromToken(token);
        Customer customer = new Customer();
        customer.setName(customerDTO.name());
        customer.setPhone(customerDTO.phone());
        customer.setEmail(customerDTO.email());
        customer.setAddress(customerDTO.address());
        customer.setMarbleshop(marbleshop);
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(UUID id) {
        if (id == null) {
            return null;
        }
        return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + id));

    }

    @Override
    public List<Customer> findAll(UUID marbleshopId) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
