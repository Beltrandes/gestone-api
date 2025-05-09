package com.gestone.gestone_api.domain.miscellaneous_material;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

public interface IMiscellaneousMaterialService {
    MiscellaneousMaterial save(MiscellaneousMaterialDTO miscellaneousMaterialDTO, HttpServletRequest request);

    MiscellaneousMaterial updatePrice(UpdateMaterialPriceDTO updateMaterialPriceDTO);

    MiscellaneousMaterial findById(UUID id);

    List<MiscellaneousMaterial> findAll(HttpServletRequest request);

    MiscellaneousMaterial update(MiscellaneousMaterialDTO miscellaneousMaterialDTO, UUID miscellaneousMaterialId);

    void delete(UUID miscellaneousMaterialId);
}
