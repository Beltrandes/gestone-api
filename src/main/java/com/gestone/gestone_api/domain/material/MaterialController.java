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
    private MaterialService materialService;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<MaterialResponseDTO> save(@RequestBody MaterialDTO materialDTO, HttpServletRequest request) {
        var savedMaterial = materialService.save(materialDTO, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MaterialResponseDTO(savedMaterial));
    }

    @GetMapping
    public ResponseEntity<List<MaterialResponseDTO>> findAll(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Marbleshop marbleshop = tokenService.getMarbleshopFromToken(token);
        List<Material> materials = marbleshop.getMaterials();
        return ResponseEntity.ok(materials.stream().map(MaterialResponseDTO::new).toList());
    }
    @PatchMapping("/update/price")
    public ResponseEntity<MaterialResponseDTO> updatePrice(@RequestBody UpdateMaterialPriceDTO updateMaterialPriceDTO) {
        var updatedMaterial = materialService.updatePrice(updateMaterialPriceDTO);
        return ResponseEntity.ok(new MaterialResponseDTO(updatedMaterial));
    }
}
