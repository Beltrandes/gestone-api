package com.gestone.gestone_api.domain.user;

public record UserRequestDTO(
    String name,
    String email,
    String password,
    String phone
    
) {
    
}
