package com.gestone.gestone_api.domain.quotation;

import java.util.UUID;

public record MiscellaneousItemDTO(
        String name,
        String description,
        Integer quantity,
        UUID miscellaneousMaterialId

) {

}
