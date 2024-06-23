package com.gestone.gestone_api.user;

public record UserRequestDTO(
    String name,
    String email,
    String password,
    String phone
    
) {
    
}
