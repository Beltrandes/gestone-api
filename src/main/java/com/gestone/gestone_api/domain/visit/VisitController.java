package com.gestone.gestone_api.domain.visit;

import com.gestone.gestone_api.domain.customer.CustomerService;
import com.gestone.gestone_api.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/visit")
public class VisitController {

    @Autowired
    private VisitService visitService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<VisitResponseDTO> save(@RequestBody VisitDTO dto, HttpServletRequest request) {
        var marbleshop = tokenService.getMarbleshopFromToken(request.getHeader("Authorization"));
        var customer = customerService.findById(dto.customerId());
        var visit = new Visit(customer, marbleshop, dto.reason(), dto.scheduledAt(), dto.notes());
        var saved = visitService.save(visit);
        return ResponseEntity.status(HttpStatus.CREATED).body(new VisitResponseDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<VisitResponseDTO>> findAll(HttpServletRequest request) {
        var marbleshop = tokenService.getMarbleshopFromToken(request.getHeader("Authorization"));
        var visits = visitService.findAll(marbleshop.getId()).stream()
                .map(VisitResponseDTO::new)
                .toList();
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/{visitId}")
    public ResponseEntity<VisitResponseDTO> findById(@PathVariable UUID visitId) {
        var visit = visitService.findById(visitId);
        return ResponseEntity.ok(new VisitResponseDTO(visit));
    }

    @PatchMapping("/{visitId}/complete")
    public ResponseEntity<VisitResponseDTO> complete(@PathVariable UUID visitId) {
        var visit = visitService.complete(visitId);
        return ResponseEntity.ok(new VisitResponseDTO(visit));
    }

    @DeleteMapping("/{visitId}")
    public ResponseEntity<Void> delete(@PathVariable UUID visitId) {
        visitService.delete(visitId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
