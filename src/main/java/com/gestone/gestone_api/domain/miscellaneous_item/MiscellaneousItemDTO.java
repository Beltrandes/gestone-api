package com.gestone.gestone_api.domain.miscellaneous_item;

import java.util.UUID;

public record MiscellaneousItemDTO(
        String name,
        String description,
        Integer quantity,
        UUID miscellaneousMaterialId

) {

}
