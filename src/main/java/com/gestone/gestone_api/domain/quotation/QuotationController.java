package com.gestone.gestone_api.domain.quotation;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/quotation")
public class QuotationController {
    @Autowired
    private QuotationService quotationService;
    @Autowired
    private QuotationPdfService pdfService;

    @PostMapping
    public ResponseEntity<QuotationResponseDTO>  save(@RequestBody QuotationDTO quotationDTO, HttpServletRequest request) {
        var savedQuotation = quotationService.save(quotationDTO, request);
        return ResponseEntity.ok(new QuotationResponseDTO(savedQuotation));
    }
    @GetMapping("/{marbleshopId}")
    public ResponseEntity<List<QuotationResponseDTO>> findAll(@PathVariable UUID marbleshopId) {
        var quotations = quotationService.findAll(marbleshopId).stream().map(quotation -> new QuotationResponseDTO(quotation)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(quotations);
    }

    @GetMapping("/pdf/{quotationId}")
    public ResponseEntity<byte[]> generateQuotationPdf(@PathVariable UUID quotationId) throws IOException {
        Quotation quotation = quotationService.findById(quotationId);
        byte[] pdfContent = pdfService.generatePdf(quotation);

        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=quotation_" + quotationId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfContent);
    }

}
