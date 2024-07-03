package com.gestone.gestone_api.domain.material;

import java.math.BigDecimal;
import java.util.UUID;

public interface IMiscellaneousMaterialService {

    MiscellaneousMaterial saveMiscellaneousMaterial(MiscellaneousMaterialDTO miscellaneousMaterial);

    MiscellaneousMaterial findById(UUID id);
    MiscellaneousMaterial updatePrice(UUID id, BigDecimal newPrice);
    void deleteMiscellaneousMaterial(UUID id);
}
