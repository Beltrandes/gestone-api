package com.gestone.gestone_api.domain.supply;

import com.gestone.gestone_api.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SupplyService implements ISupplyService {

    @Autowired
    private SupplyRepository supplyRepository;

    @Autowired
    private SupplyMovementRepository supplyMovementRepository;

    @Override
    public Supply createSupply(Supply supply) {
        return supplyRepository.save(supply);
    }

    @Override
    public Supply updateSupply(UUID supplyId, Supply updatedData) {
        var supply = this.findSupplyById(supplyId);
        supply.setName(updatedData.getName());
        supply.setDescription(updatedData.getDescription());
        supply.setUnitOfMeasure(updatedData.getUnitOfMeasure());
        supply.setMinimumStock(updatedData.getMinimumStock());
        return supplyRepository.save(supply);
    }

    @Override
    public Supply findSupplyById(UUID id) {
        return supplyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Supply not found with id: " + id));
    }

    @Override
    public List<Supply> findAllActiveSupplies(UUID marbleshopId) {
        return supplyRepository.findAllByMarbleshopIdAndActiveTrueOrderByNameAsc(marbleshopId);
    }

    @Override
    public void deleteSupply(UUID id) {
        var supply = this.findSupplyById(id);
        supply.setActive(false); // Soft delete
        supplyRepository.save(supply);
    }

    @Override
    @Transactional
    public SupplyMovement registerMovement(UUID supplyId, SupplyMovementRequestDTO dto, User userAuth) {
        var supply = this.findSupplyById(supplyId);
        
        // Registrar o movimento
        var movement = new SupplyMovement(
                supply,
                dto.type(),
                dto.category(),
                dto.quantity(),
                dto.notes(),
                userAuth
        );
        supplyMovementRepository.save(movement);

        // Atualizar o estoque na entidade mestre
        int multiplier = dto.type() == SupplyMovementType.IN ? 1 : -1;
        int newStock = supply.getCurrentStock() + (dto.quantity() * multiplier);
        
        if (newStock < 0) {
            throw new IllegalArgumentException("Estoque insuficiente para realizar esta saída.");
        }
        
        supply.setCurrentStock(newStock);
        supplyRepository.save(supply);
        
        return movement;
    }

    @Override
    public List<SupplyMovement> findAllMovements(UUID supplyId) {
        return supplyMovementRepository.findAllBySupplyIdOrderByCreatedAtDesc(supplyId);
    }
}
