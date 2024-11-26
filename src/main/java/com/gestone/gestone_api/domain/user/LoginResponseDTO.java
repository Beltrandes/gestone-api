package com.gestone.gestone_api.domain.user;

import java.util.UUID;

public record LoginResponseDTO(String token, UUID marbleshopId) {
}
