package com.gestone.gestone_api.domain.customer;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

public interface ICustomerService {

    Customer save(CustomerDTO customerDTO, HttpServletRequest request);

    Customer findById(UUID id);

    List<Customer> findAll(UUID marbleshopId);

    void delete(UUID id);
}
