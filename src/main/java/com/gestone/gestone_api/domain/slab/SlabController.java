package com.gestone.gestone_api.domain.slab;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
