package com.gestone.gestone_api.domain.marbleshop_material;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.miscellaneous_material.UpdateMaterialPriceDTO;
import com.gestone.gestone_api.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MarbleshopMaterialService implements IMarbleshopMaterialService {
    @Autowired
    private MarbleshopMaterialRepository marbleshopMaterialRepository;
    @Autowired
    private TokenService tokenService;

    public MarbleshopMaterial save(MarbleshopMaterialDTO marbleshopMaterialDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Marbleshop marbleshop = tokenService.getMarbleshopFromToken(token);
        MarbleshopMaterial marbleshopMaterial = new MarbleshopMaterial();
        marbleshopMaterial.setName(marbleshopMaterialDTO.name());
        marbleshopMaterial.setDetails(marbleshopMaterialDTO.details());
        marbleshopMaterial.setPrice(marbleshopMaterialDTO.price());
        marbleshopMaterial.setMaterialType(marbleshopMaterialDTO.marbleshopMaterialType());
        marbleshopMaterial.setMarbleshop(marbleshop);
        return marbleshopMaterialRepository.save(marbleshopMaterial);
    }

    @Override
    public MarbleshopMaterial findById(UUID id) {
        if (id == null) {
            return null;
        }
        return marbleshopMaterialRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Material not found with id: " + id));
    }

    @Override
    public List<MarbleshopMaterial> findAll(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Marbleshop marbleshop = tokenService.getMarbleshopFromToken(token);
        return marbleshopMaterialRepository.findByMarbleshop_IdAndActiveTrue(marbleshop.getId());
    }
    @Override
    public MarbleshopMaterial update(MarbleshopMaterialDTO marbleshopMaterialDTO, UUID marbleshopMaterialId) {
        MarbleshopMaterial marbleshopMaterial = findById(marbleshopMaterialId);
        marbleshopMaterial.setName(marbleshopMaterialDTO.name());
        marbleshopMaterial.setDetails(marbleshopMaterialDTO.details());
        marbleshopMaterial.setLastPrice(marbleshopMaterial.getPrice());
        marbleshopMaterial.setPrice(marbleshopMaterialDTO.price());
        marbleshopMaterial.setMaterialType(marbleshopMaterialDTO.marbleshopMaterialType());
        return marbleshopMaterialRepository.save(marbleshopMaterial);
    }

    public MarbleshopMaterial updatePrice(UpdateMaterialPriceDTO updateMaterialPriceDTO) {
        MarbleshopMaterial marbleshopMaterial = findById(updateMaterialPriceDTO.materialId());
        marbleshopMaterial.setLastPrice(marbleshopMaterial.getPrice());
        marbleshopMaterial.setPrice(updateMaterialPriceDTO.price());
        return marbleshopMaterialRepository.save(marbleshopMaterial);
    }

    @Override
    public void delete(UUID marbleshopMaterialId) {
        var marbleshopMaterial = findById(marbleshopMaterialId);
        marbleshopMaterial.setActive(false);
        marbleshopMaterialRepository.save(marbleshopMaterial);
    }

}
