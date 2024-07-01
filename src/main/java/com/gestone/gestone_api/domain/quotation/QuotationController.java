package com.gestone.gestone_api.domain.quotation;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/quotation")
public class QuotationController {
    @Autowired
    private QuotationService quotationService;

    @PostMapping
    public ResponseEntity<QuotationResponseDTO>  save(@RequestBody QuotationDTO quotationDTO, HttpServletRequest request) {
        var savedQuotation = quotationService.save(quotationDTO, request);
        return ResponseEntity.ok(new QuotationResponseDTO(savedQuotation));
    }
    @GetMapping
    public ResponseEntity<List<QuotationResponseDTO>> findAll(HttpServletRequest request) {
        var quotations = quotationService.findAll(request);
        return ResponseEntity.ok(quotations.stream().map(QuotationResponseDTO::new).toList());
    }
}
