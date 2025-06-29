package com.gestone.gestone_api.domain.slab;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.marbleshop_material.MarbleshopMaterial;
import com.gestone.gestone_api.domain.marbleshop_material.MarbleshopMaterialService;
import com.gestone.gestone_api.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SlabService {
    @Autowired
    private SlabRepository slabRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MarbleshopMaterialService materialService;

    public Slab save(SlabRequestDTO slabRequestDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Marbleshop marbleshop = tokenService.getMarbleshopFromToken(token);
        MarbleshopMaterial material = materialService.findById(slabRequestDTO.materialId());
        Slab slab = new Slab();
        slab.setMarbleshop(marbleshop);
        slab.setMaterial(material);
        slab.setMeasureX(slabRequestDTO.measureX());
        slab.setMeasureY(slabRequestDTO.measureY());
        slab.setDensityMeasure(slabRequestDTO.densityMeasure());
        slab.setQuality(slabRequestDTO.quality());
        slab.setEntryDate(slabRequestDTO.entryDate());
        slab.setNotes(slabRequestDTO.notes());
        slab.setStatus(slabRequestDTO.status());
        slab.calculateArea();
        return slabRepository.save(slab);
    }

    public Slab findById(UUID slabId) {
        if (slabId == null) {
            return null;
        }
        return slabRepository.findById(slabId).orElseThrow(() -> new IllegalArgumentException("Slab not found with id: " + slabId) );
    }

}
