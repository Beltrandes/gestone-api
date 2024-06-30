package com.gestone.gestone_api.domain.customer;

public record CustomerDTO(
        String name,
        String phone,
        String email,
        String address
) {
}
