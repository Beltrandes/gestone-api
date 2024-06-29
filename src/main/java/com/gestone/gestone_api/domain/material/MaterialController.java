package com.gestone.gestone_api.domain.material;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @PostMapping
    public ResponseEntity<MaterialResponseDTO> save(@RequestBody MaterialDTO materialDTO, HttpServletRequest request) {
        var savedMaterial = materialService.save(materialDTO, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MaterialResponseDTO(savedMaterial));
    }
}
