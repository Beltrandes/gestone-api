package com.gestone.gestone_api.domain.material;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MiscellaneousMaterialService implements IMiscellaneousMaterialService {
    @Autowired
    private MiscellaneousMaterialRepository miscellaneousMaterialRepository;
    @Autowired
    private TokenService tokenService;

    @Override
    public MiscellaneousMaterial save(MiscellaneousMaterialDTO miscellaneousMaterialDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Marbleshop marbleshop = tokenService.getMarbleshopFromToken(token);
        MiscellaneousMaterial miscellaneousMaterial = new MiscellaneousMaterial();
        miscellaneousMaterial.setName(miscellaneousMaterialDTO.name());
        miscellaneousMaterial.setDetails(miscellaneousMaterialDTO.details());
        miscellaneousMaterial.setMiscellaneousMaterialType(miscellaneousMaterialDTO.miscellaneousMaterialType());
        miscellaneousMaterial.setPrice(miscellaneousMaterialDTO.price());
        miscellaneousMaterial.setMarbleshop(marbleshop);
        return miscellaneousMaterialRepository.save(miscellaneousMaterial);
    }

    @Override
    public MiscellaneousMaterial updatePrice(UpdateMaterialPriceDTO updateMaterialPriceDTO) {
        MiscellaneousMaterial miscellaneousMaterial = findById(updateMaterialPriceDTO.materialId());
        miscellaneousMaterial.setLastPrice(miscellaneousMaterial.getPrice());
        miscellaneousMaterial.setPrice(updateMaterialPriceDTO.price());
        return miscellaneousMaterialRepository.save(miscellaneousMaterial);
    }

    @Override
    public MiscellaneousMaterial findById(UUID id) {
        return miscellaneousMaterialRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Miscellaneous Material not found with id: " + id));
    }

    @Override
    public MiscellaneousMaterial update(MiscellaneousMaterialDTO miscellaneousMaterialDTO, UUID miscellaneousMaterialId) {
        MiscellaneousMaterial miscellaneousMaterial = findById(miscellaneousMaterialId);
        miscellaneousMaterial.setName(miscellaneousMaterialDTO.name());
        miscellaneousMaterial.setDetails(miscellaneousMaterialDTO.details());
        miscellaneousMaterial.setLastPrice(miscellaneousMaterial.getPrice());
        miscellaneousMaterial.setPrice(miscellaneousMaterialDTO.price());
        miscellaneousMaterial.setMiscellaneousMaterialType(miscellaneousMaterialDTO.miscellaneousMaterialType());
        return miscellaneousMaterialRepository.save(miscellaneousMaterial);
    }

    @Override
    public List<MiscellaneousMaterial> findAll(HttpServletRequest request) {
        return null;
    }

    @Override
    public void delete(UUID miscellaneousMaterialId) {
        var miscellaneousMaterial = findById(miscellaneousMaterialId);
        miscellaneousMaterialRepository.delete(miscellaneousMaterial);
    }
}
