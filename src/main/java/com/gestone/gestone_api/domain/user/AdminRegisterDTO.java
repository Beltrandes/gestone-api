package com.gestone.gestone_api.domain.user;

import com.gestone.gestone_api.domain.marbleshop.MarbleshopRequestDTO;

public record AdminRegisterDTO(
    String name,
    String email,
    String password,
    String phone,
    MarbleshopRequestDTO marbleshop
) {

}
