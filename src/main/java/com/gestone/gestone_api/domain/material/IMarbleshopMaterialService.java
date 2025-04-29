package com.gestone.gestone_api.domain.material;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

public interface IMarbleshopMaterialService {

    MarbleshopMaterial save(MarbleshopMaterialDTO marbleshopMaterialDTO, HttpServletRequest request);

    MarbleshopMaterial findById(UUID id);

    List<MarbleshopMaterial> findAll(HttpServletRequest request);

    MarbleshopMaterial update(MarbleshopMaterialDTO marbleshopMaterialDTO, UUID marbleshopMaterialId);

    void delete(UUID marbleshopMaterialId);

}
