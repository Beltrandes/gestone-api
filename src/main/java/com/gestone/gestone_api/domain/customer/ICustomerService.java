package com.gestone.gestone_api.domain.customer;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

public interface ICustomerService {

    Customer save(Customer customer);

    Customer findById(UUID id);

    Customer update(Customer customer, UUID customerId);

    List<Customer> findAll(UUID marbleshopId);

    void delete(UUID id);
}
