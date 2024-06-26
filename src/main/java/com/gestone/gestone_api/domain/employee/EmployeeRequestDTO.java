package com.gestone.gestone_api.domain.employee;

import java.math.BigDecimal;

public record EmployeeRequestDTO(
        String name,
        String documentNumber,
        BigDecimal salary,
        String phone,
        EmployeeRole employeeRole
) {
}
