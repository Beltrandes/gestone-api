package com.gestone.gestone_api.domain.user;

import java.util.UUID;

public record ApprovedEmployeeDTO(
        String email,
        UUID employeeId
) {
}
