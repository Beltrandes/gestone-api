package com.gestone.gestone_api.domain.supply;

import com.gestone.gestone_api.domain.user.User;

import java.util.List;
import java.util.UUID;

public interface ISupplyService {
    Supply createSupply(Supply supply);
    Supply updateSupply(UUID supplyId, Supply supply);
    Supply findSupplyById(UUID id);
    List<Supply> findAllActiveSupplies(UUID marbleshopId);
    void deleteSupply(UUID id);
    
    SupplyMovement registerMovement(UUID supplyId, SupplyMovementRequestDTO dto, User userAuth);
    List<SupplyMovement> findAllMovements(UUID supplyId);
}
