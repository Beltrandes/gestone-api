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
    public Customer save(Customer customer) {
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
    public Customer update(Customer customer, UUID customerId) {
        var customerSaved = this.findById(customerId);
        customerSaved.setName(customer.getName());
        customerSaved.setPhone(customer.getPhone());
        customerSaved.setEmail(customer.getEmail());
        customerSaved.setAddress(customer.getAddress());
        return customerRepository.save(customerSaved);

    }

    @Override
    public List<Customer> findAll(UUID marbleshopId) {
       return customerRepository.findAllByMarbleshopId(marbleshopId);
    }



    @Override
    public void delete(UUID id) {
       var customer = this.findById(id);
       customerRepository.delete(customer);
    }
}
