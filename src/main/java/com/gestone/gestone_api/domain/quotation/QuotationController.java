package com.gestone.gestone_api.domain.quotation;

import com.gestone.gestone_api.domain.customer.CustomerService;
import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopItem;
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
    private CustomerService customerService;
    @Autowired
    private QuotationPdfService pdfService;

    @PostMapping
    public ResponseEntity<QuotationResponseDTO>  save(@RequestBody QuotationDTO quotationDTO, HttpServletRequest request) {
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

    @GetMapping("/pdf/{quotationId}")
    public ResponseEntity<byte[]> generateQuotationPdf(@PathVariable UUID quotationId) throws Exception {
        Quotation quotation = quotationService.findById(quotationId);
        byte[] pdfContent = pdfService.generateQuotationPdf(quotation);
        String[] splittedId = quotation.getId().toString().split("-");
        String shortId = splittedId[splittedId.length - 1];
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=orcamento- " + shortId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfContent);
    }

    @PutMapping("/{quotationId}")
    public ResponseEntity<QuotationResponseDTO> updateQuotation(@PathVariable UUID quotationId, @RequestBody QuotationDTO quotationDTO) {
       var quotation = this.quotationService.update(quotationId, quotationDTO);
       return ResponseEntity.status(HttpStatus.OK).body(new QuotationResponseDTO(quotation));

    }

}
