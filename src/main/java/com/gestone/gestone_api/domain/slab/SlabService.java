package com.gestone.gestone_api.domain.slab;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.marbleshop_material.MarbleshopMaterial;
import com.gestone.gestone_api.domain.marbleshop_material.MarbleshopMaterialService;
import com.gestone.gestone_api.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return slabRepository.findById(slabId)
                .filter(Slab::isActive)
                .orElseThrow(() -> new IllegalArgumentException("Slab not found or inactive with id: " + slabId));
    }

    public List<Slab> findAll(UUID marbleshopId) {
        if (marbleshopId == null) {
            return null;
        }
        return slabRepository.findByMarbleshopIdAndActiveTrue(marbleshopId);
    }

    public Slab update(UUID id, SlabRequestDTO dto) {
        Slab slab = this.findById(id);

        slab.setMeasureX(dto.measureX());
        slab.setMeasureY(dto.measureY());
        slab.setDensityMeasure(dto.densityMeasure());
        slab.setQuality(dto.quality());
        
        if (dto.entryDate() != null) slab.setEntryDate(dto.entryDate());
        if (dto.notes() != null) slab.setNotes(dto.notes());
        if (dto.status() != null) slab.setStatus(dto.status());
        if (dto.materialId() != null) {
            MarbleshopMaterial material = materialService.findById(dto.materialId());
            slab.setMaterial(material);
        }

        slab.calculateArea();
        this.updateStatusBasedOnArea(slab);

        return slabRepository.save(slab);
    }

    public void delete(UUID id) {
        Slab slab = this.findById(id);
        slab.setActive(false);
        slabRepository.save(slab);
    }

    public void updateStatusBasedOnArea(Slab slab) {
        if (slab.getStatus() == SlabStatus.DISCARDED) {
            return;
        }
        
        // If area is 0 or extremely close to 0 due to precision issues
        if (slab.getArea().compareTo(java.math.BigDecimal.valueOf(0.001)) < 0) {
            slab.setStatus(SlabStatus.IN_USE); 
        } else if (slab.getStatus() == SlabStatus.IN_USE && slab.getArea().compareTo(java.math.BigDecimal.valueOf(0.001)) >= 0) {
            slab.setStatus(SlabStatus.IN_STOCK);
        }
    }

}
