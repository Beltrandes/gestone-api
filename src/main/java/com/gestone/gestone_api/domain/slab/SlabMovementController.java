package com.gestone.gestone_api.domain.slab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/slab/movement")
public class SlabMovementController {

    @Autowired
    private SlabMovementService slabMovementService;

    @PostMapping
    public ResponseEntity<SlabMovementResponseDTO> saveSlabMovement(@RequestBody SlabMovementRequestDTO request) {
        SlabMovement slabMovement = slabMovementService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SlabMovementResponseDTO(slabMovement));
    }

    @org.springframework.web.bind.annotation.GetMapping("/history/{slabId}")
    public ResponseEntity<java.util.List<SlabMovementResponseDTO>> getHistory(@org.springframework.web.bind.annotation.PathVariable java.util.UUID slabId) {
        var movements = slabMovementService.findAllBySlabId(slabId)
                .stream().map(SlabMovementResponseDTO::new).toList();
        return ResponseEntity.ok(movements);
    }
}
