package com.gestone.gestone_api.domain.customer;

import java.util.UUID;

public record CustomerDTO(
        String name,
        String phone,
        String email,
        String address
) {
    Customer toEntity() {
        return new Customer(
                this.name,
                this.phone,
                this.email,
                this.address
        );
    }
}
