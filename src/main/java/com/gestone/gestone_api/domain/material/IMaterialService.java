package com.gestone.gestone_api.domain.material;

import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface IMaterialService {

    Material save(MaterialDTO materialDTO, HttpServletRequest request);

    Material findById(UUID id);
}
