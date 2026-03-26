package com.gestone.gestone_api.domain.quotation;

import com.gestone.gestone_api.domain.customer.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/quotation")
public class QuotationController {
    @Autowired
    private QuotationService quotationService;
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<QuotationResponseDTO> save(@RequestBody QuotationDTO quotationDTO,
            HttpServletRequest request) {
        var savedQuotation = quotationService.save(quotationDTO, request);
        return ResponseEntity.ok(new QuotationResponseDTO(savedQuotation));
    }

    @GetMapping("/{marbleshopId}")
    public ResponseEntity<List<QuotationResponseDTO>> findAll(@PathVariable UUID marbleshopId) {
        var quotations = quotationService.findAll(marbleshopId).stream().map(QuotationResponseDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(quotations);
    }

    @GetMapping("/id/{quotationId}")
    public ResponseEntity<QuotationResponseDTO> findQuotationById(@PathVariable UUID quotationId) {
        var quotation = quotationService.findById(quotationId);
        return ResponseEntity.status(HttpStatus.OK).body(new QuotationResponseDTO(quotation));
    }

    @PutMapping("/{quotationId}")
    public ResponseEntity<QuotationResponseDTO> updateQuotation(@PathVariable UUID quotationId,
            @RequestBody QuotationDTO quotationDTO) {
        var quotation = this.quotationService.update(quotationId, quotationDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new QuotationResponseDTO(quotation));

    }

    @PutMapping("/recalculate/{quotationId}")
    public ResponseEntity<QuotationResponseDTO> recalculateQuotation(@PathVariable UUID quotationId) {
        Quotation quotation = this.quotationService.recalculateQuotation(quotationId);
        return ResponseEntity.ok().body(new QuotationResponseDTO(quotation));
    }

    @PatchMapping("/marbleshop-item/{marbleshopItemId}")
    public ResponseEntity<Void> updateMarbleshopItemValue(@PathVariable UUID marbleshopItemId,
            @RequestBody UpdateItemValueRequest request) {
        quotationService.updateQuotationItemPrice(marbleshopItemId, request);
        return ResponseEntity.noContent().build();
    }

}
