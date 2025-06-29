package com.gestone.gestone_api.domain.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record EmployeeRequestDTO(
        String name,
        String documentNumber,
        BigDecimal salary,
        String phone,
        EmployeeRole employeeRole,
        UUID userId,
        LocalDate hireDate

) {
}
