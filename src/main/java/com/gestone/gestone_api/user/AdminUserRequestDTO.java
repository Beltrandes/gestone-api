package com.gestone.gestone_api.user;

import com.gestone.gestone_api.marbleshop.MarbleshopRequestDTO;

public record AdminUserRequestDTO(
    String name,
    String email,
    String password,
    String phone,
    MarbleshopRequestDTO marbleshop
) {
    
}
