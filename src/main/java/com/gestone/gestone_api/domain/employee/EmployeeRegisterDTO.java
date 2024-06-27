package com.gestone.gestone_api.domain.employee;

public record EmployeeRegisterDTO(
        String name,
        String email,
        String password,
        String phone
        ) {

}
