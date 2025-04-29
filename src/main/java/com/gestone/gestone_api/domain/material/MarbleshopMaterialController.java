package com.gestone.gestone_api.domain.material;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/material")
public class MarbleshopMaterialController {
    @Autowired
    private MarbleshopMaterialService marbleshopMaterialService;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<MarbleshopMaterialResponseDTO> saveMarbleshopMaterial(@RequestBody MarbleshopMaterialDTO marbleshopMaterialDTO, HttpServletRequest request) {
        var savedMarbleshopMaterial = marbleshopMaterialService.save(marbleshopMaterialDTO, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MarbleshopMaterialResponseDTO(savedMarbleshopMaterial));
    }

    @GetMapping
    public ResponseEntity<List<MarbleshopMaterialResponseDTO>> findAllMarbleshopMaterial(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Marbleshop marbleshop = tokenService.getMarbleshopFromToken(token);
        List<MarbleshopMaterial> materials = marbleshop.getMarbleshopMaterials();
        return ResponseEntity.ok(materials.stream().map(MarbleshopMaterialResponseDTO::new).toList());
    }

    @PutMapping("/{marbleshopMaterialId}")
    public ResponseEntity<MarbleshopMaterialResponseDTO> updateMarbleshopMaterial(@RequestBody MarbleshopMaterialDTO marbleshopMaterialDTO, @PathVariable UUID marbleshopMaterialId) {
        var updatedMaterial = marbleshopMaterialService.update(marbleshopMaterialDTO,marbleshopMaterialId);
        return ResponseEntity.status(HttpStatus.OK).body(new MarbleshopMaterialResponseDTO(updatedMaterial));
    }

    @PatchMapping("/update/price")
    public ResponseEntity<MarbleshopMaterialResponseDTO> updatePrice(@RequestBody UpdateMaterialPriceDTO updateMaterialPriceDTO) {
        var updatedMaterial = marbleshopMaterialService.updatePrice(updateMaterialPriceDTO);
        return ResponseEntity.ok(new MarbleshopMaterialResponseDTO(updatedMaterial));
    }

    @DeleteMapping("/{marbleshopMaterialId}")
    public ResponseEntity<Void> delete(@PathVariable UUID marbleshopMaterialId) {
        marbleshopMaterialService.delete(marbleshopMaterialId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
