package com.gestone.gestone_api.domain.slab;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/slab")
public class SlabController {

    @Autowired
    private SlabService slabService;

    @PostMapping
    public ResponseEntity<SlabResponseDTO>  saveSlab(@RequestBody SlabRequestDTO slabRequestDTO, HttpServletRequest request) {
        Slab slab = slabService.save(slabRequestDTO, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SlabResponseDTO(slab));

    }

    @GetMapping("/{slabId}")
    public ResponseEntity<SlabResponseDTO> findById(@PathVariable UUID slabId) {
        var slab = slabService.findById(slabId);
        return ResponseEntity.ok(new SlabResponseDTO(slab));
    }

    @GetMapping("/marbleshop/{marbleshopId}")
    public ResponseEntity<List<SlabResponseDTO>> findAllSlabs(@PathVariable UUID marbleshopId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.slabService.findAll(marbleshopId).stream().map(SlabResponseDTO::new).toList());
    }

    @PutMapping("/{slabId}")
    public ResponseEntity<SlabResponseDTO> updateSlab(@PathVariable UUID slabId, @RequestBody SlabRequestDTO slabRequestDTO) {
        Slab updatedSlab = slabService.update(slabId, slabRequestDTO);
        return ResponseEntity.ok(new SlabResponseDTO(updatedSlab));
    }

    @DeleteMapping("/{slabId}")
    public ResponseEntity<Void> deleteSlab(@PathVariable UUID slabId) {
        slabService.delete(slabId);
        return ResponseEntity.noContent().build();
    }
}
