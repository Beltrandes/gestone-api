package com.gestone.gestone_api.domain.supply;

import com.gestone.gestone_api.infra.security.TokenService;
import com.gestone.gestone_api.domain.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/supply")
public class SupplyController {

    @Autowired
    private SupplyService supplyService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<SupplyResponseDTO> create(@RequestBody SupplyRequestDTO dto, HttpServletRequest request) {
        var marbleshop = tokenService.getMarbleshopFromToken(request.getHeader("Authorization"));
        var supply = new Supply(dto.name(), dto.description(), dto.unitOfMeasure(), dto.minimumStock(), marbleshop);
        var saved = supplyService.createSupply(supply);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SupplyResponseDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<SupplyResponseDTO>> listAll(HttpServletRequest request) {
        var marbleshop = tokenService.getMarbleshopFromToken(request.getHeader("Authorization"));
        var supplies = supplyService.findAllActiveSupplies(marbleshop.getId())
                .stream().map(SupplyResponseDTO::new).toList();
        return ResponseEntity.ok(supplies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplyResponseDTO> update(@PathVariable UUID id, @RequestBody SupplyRequestDTO dto) {
        var supply = new Supply();
        supply.setName(dto.name());
        supply.setDescription(dto.description());
        supply.setUnitOfMeasure(dto.unitOfMeasure());
        supply.setMinimumStock(dto.minimumStock());
        var updated = supplyService.updateSupply(id, supply);
        return ResponseEntity.ok(new SupplyResponseDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        supplyService.deleteSupply(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Movimentações
    @PostMapping("/movement/{supplyId}")
    public ResponseEntity<SupplyMovementResponseDTO> registerMovement(
            @PathVariable UUID supplyId,
            @RequestBody @Valid SupplyMovementRequestDTO dto,
            HttpServletRequest request) {

        var email = tokenService.getUserEmailFromToken(request.getHeader("Authorization").replace("Bearer ", ""));
        var userAuth = (com.gestone.gestone_api.domain.user.User) userRepository.findByEmail(email); // Fetching user
                                                                                                     // who made the
                                                                                                     // request

        var movement = supplyService.registerMovement(supplyId, dto, userAuth);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SupplyMovementResponseDTO(movement));
    }

    @GetMapping("/movement/{supplyId}")
    public ResponseEntity<List<SupplyMovementResponseDTO>> listMovements(@PathVariable UUID supplyId) {
        var movements = supplyService.findAllMovements(supplyId)
                .stream().map(SupplyMovementResponseDTO::new).toList();
        return ResponseEntity.ok(movements);
    }
}
