package com.gestone.gestone_api.domain.material;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/material")
public class MaterialController {
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
    @PatchMapping("/update/price")
    public ResponseEntity<MarbleshopMaterialResponseDTO> updatePrice(@RequestBody UpdateMaterialPriceDTO updateMaterialPriceDTO) {
        var updatedMaterial = marbleshopMaterialService.updatePrice(updateMaterialPriceDTO);
        return ResponseEntity.ok(new MarbleshopMaterialResponseDTO(updatedMaterial));
    }
}
