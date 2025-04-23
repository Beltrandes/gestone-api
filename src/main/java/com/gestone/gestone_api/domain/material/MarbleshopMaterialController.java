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
public class MarbleshopMaterialController {
    @Autowired
    private MarbleshopMaterialService marbleshopMaterialService;
    @Autowired
    private MiscellaneousMaterialService miscellaneousMaterialService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/marbleshop")
    public ResponseEntity<MarbleshopMaterialResponseDTO> saveMarbleshopMaterial(@RequestBody MarbleshopMaterialDTO marbleshopMaterialDTO, HttpServletRequest request) {
        var savedMarbleshopMaterial = marbleshopMaterialService.save(marbleshopMaterialDTO, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MarbleshopMaterialResponseDTO(savedMarbleshopMaterial));
    }

    @PostMapping("/miscellaneous")
    public ResponseEntity<MiscellaneousMaterialResponseDTO> saveMiscellaneousMaterial(@RequestBody MiscellaneousMaterialDTO miscellaneousMaterialDTO, HttpServletRequest request) {
        var savedMarbleshopMaterial = miscellaneousMaterialService.save(miscellaneousMaterialDTO, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MiscellaneousMaterialResponseDTO(savedMarbleshopMaterial));
    }

    @GetMapping("/marbleshop")
    public ResponseEntity<List<MarbleshopMaterialResponseDTO>> findAllMarbleshopMaterial(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Marbleshop marbleshop = tokenService.getMarbleshopFromToken(token);
        List<MarbleshopMaterial> materials = marbleshop.getMarbleshopMaterials();
        return ResponseEntity.ok(materials.stream().map(MarbleshopMaterialResponseDTO::new).toList());
    }

    @GetMapping("/miscellaneous")
    public ResponseEntity<List<MiscellaneousMaterialResponseDTO>> findAllMiscellaneousMaterial(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Marbleshop marbleshop = tokenService.getMarbleshopFromToken(token);
        List<MiscellaneousMaterial> materials = marbleshop.getMiscellaneousMaterials();
        return ResponseEntity.ok(materials.stream().map(MiscellaneousMaterialResponseDTO::new).toList());
    }
    @PatchMapping("/update/price")
    public ResponseEntity<MarbleshopMaterialResponseDTO> updatePrice(@RequestBody UpdateMaterialPriceDTO updateMaterialPriceDTO) {
        var updatedMaterial = marbleshopMaterialService.updatePrice(updateMaterialPriceDTO);
        return ResponseEntity.ok(new MarbleshopMaterialResponseDTO(updatedMaterial));
    }
}
