package com.gestone.gestone_api.domain.material;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private TokenService tokenService;

    public Material save(MaterialDTO materialDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Marbleshop marbleshop = tokenService.getMarbleshopFromToken(token);
        Material material = new Material();
        material.setName(materialDTO.name());
        material.setDetails(materialDTO.details());
        material.setPrice(materialDTO.price());
        material.setMaterialType(materialDTO.materialType());
        material.setMarbleshop(marbleshop);
        return materialRepository.save(material);
    }
}
