package com.gestone.gestone_api.domain.material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class MiscellaneousMaterialService implements IMiscellaneousMaterialService {
    @Autowired
    private MiscellaneousMaterialRepository miscellaneousMaterialRepository;

    @Override
    public MiscellaneousMaterial saveMiscellaneousMaterial(MiscellaneousMaterialDTO miscellaneousMaterialDTO) {
        MiscellaneousMaterial miscellaneousMaterial = new MiscellaneousMaterial();
        miscellaneousMaterial.setName(miscellaneousMaterialDTO.name());
        miscellaneousMaterial.setDetails(miscellaneousMaterialDTO.details());
        miscellaneousMaterial.setPrice(miscellaneousMaterialDTO.price());
        miscellaneousMaterial.setMiscellaneousMaterialType(miscellaneousMaterialDTO.miscellaneousMaterialType());

        return miscellaneousMaterialRepository.save(miscellaneousMaterial);
    }

    @Override
    public MiscellaneousMaterial findById(UUID id) {
        return miscellaneousMaterialRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Material not found with id: " + id));
    }

    @Override
    public MiscellaneousMaterial updatePrice(UUID id, BigDecimal newPrice) {
        MiscellaneousMaterial miscellaneousMaterial = findById(id);
        miscellaneousMaterial.setPrice(newPrice);
        return miscellaneousMaterialRepository.save(miscellaneousMaterial);
    }

    @Override
    public void deleteMiscellaneousMaterial(UUID id) {
        MiscellaneousMaterial miscellaneousMaterial = findById(id);
        miscellaneousMaterialRepository.delete(miscellaneousMaterial);
    }
}
