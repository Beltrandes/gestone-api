package com.gestone.gestone_api.domain.material;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

public interface IMiscellaneousMaterialService {
    MiscellaneousMaterial save(MiscellaneousMaterialDTO miscellaneousMaterialDTO, HttpServletRequest request);

    void deleteById(UUID id);
    MiscellaneousMaterial updatePrice(UpdateMaterialPriceDTO updateMaterialPriceDTO);

    MiscellaneousMaterial findById(UUID id);

    List<MiscellaneousMaterial> findAll(HttpServletRequest request);
}
