package com.gestone.gestone_api.domain.miscellaneous_material;

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
@RequestMapping("api/v1/miscellaneous-material")
public class MiscellaneousMaterialController {
    @Autowired
    private MiscellaneousMaterialService miscellaneousMaterialService;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<MiscellaneousMaterialResponseDTO> saveMiscellaneousMaterial(@RequestBody MiscellaneousMaterialDTO miscellaneousMaterialDTO, HttpServletRequest request) {
        var savedMiscellaneousMaterialMaterial = miscellaneousMaterialService.save(miscellaneousMaterialDTO, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MiscellaneousMaterialResponseDTO(savedMiscellaneousMaterialMaterial));
    }

    @GetMapping
    public ResponseEntity<List<MiscellaneousMaterialResponseDTO>> findAllMiscellaneousMaterial(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Marbleshop marbleshop = tokenService.getMarbleshopFromToken(token);
        List<MiscellaneousMaterial> materials = marbleshop.getMiscellaneousMaterials();
        return ResponseEntity.ok(materials.stream().map(MiscellaneousMaterialResponseDTO::new).toList());
    }

    @PutMapping("/{miscellaneousMaterialId}")
    public ResponseEntity<MiscellaneousMaterialResponseDTO> updateMiscellaneousMaterial(@RequestBody MiscellaneousMaterialDTO miscellaneousMaterialDTO, @PathVariable UUID miscellaneousMaterialId) {
        var updatedMaterial = miscellaneousMaterialService.update(miscellaneousMaterialDTO,miscellaneousMaterialId);
        return ResponseEntity.status(HttpStatus.OK).body(new MiscellaneousMaterialResponseDTO(updatedMaterial));
    }

    @PatchMapping("/update/price")
    public ResponseEntity<MiscellaneousMaterialResponseDTO> updatePrice(@RequestBody UpdateMaterialPriceDTO updateMaterialPriceDTO) {
        var updatedMaterial = miscellaneousMaterialService.updatePrice(updateMaterialPriceDTO);
        return ResponseEntity.ok(new MiscellaneousMaterialResponseDTO(updatedMaterial));
    }

    @DeleteMapping("/{miscellaneousMaterialId}")
    public ResponseEntity<Void> delete(@PathVariable UUID miscellaneousMaterialId) {
        miscellaneousMaterialService.delete(miscellaneousMaterialId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
