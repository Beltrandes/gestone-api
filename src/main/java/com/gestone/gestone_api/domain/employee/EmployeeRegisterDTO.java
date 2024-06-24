package com.gestone.gestone_api.domain.employee;

import com.gestone.gestone_api.domain.marbleshop.MarbleshopRequestDTO;

public record EmployeeRegisterDTO(
        String name,
        String email,
        String password,
        String phone,
        MarbleshopRequestDTO marbleshop) {

}
