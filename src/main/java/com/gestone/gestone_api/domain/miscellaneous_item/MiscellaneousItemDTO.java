package com.gestone.gestone_api.domain.miscellaneous_item;

import java.util.UUID;

public record MiscellaneousItemDTO(
        UUID id,
        String name,
        String details,
        Integer quantity,
        UUID miscellaneousMaterialId,
        UUID quotationId

) {

}
