package com.gestone.gestone_api.domain.order;

import com.gestone.gestone_api.domain.quotation.Quotation;
import com.gestone.gestone_api.domain.quotation.QuotationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/order")
public class MarbleshopOrderController {

    @Autowired
    private MarbleshopOrderService marbleshopOrderService;
    @Autowired
    private MarbleshopOrderPdfService pdfService;


    @PostMapping
    ResponseEntity<MarbleshopOrderResponseDTO> saveMarbleshopOrder(@RequestBody MarbleshopOrderDTO marbleshopOrderDTO) {
        var savedMarbleshopOrder = this.marbleshopOrderService.save(marbleshopOrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MarbleshopOrderResponseDTO(savedMarbleshopOrder));
    }

    @GetMapping("/{marbleshopId}")
    public ResponseEntity<List<MarbleshopOrderResponseDTO>> findAllMarbleshopOrders(@PathVariable UUID marbleshopId) {
        var orders = marbleshopOrderService.findAll(marbleshopId).stream().map(MarbleshopOrderResponseDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }


    @GetMapping("/pdf/{marbleshopOrderId}")
    public ResponseEntity<byte[]> generateQuotationPdf(@PathVariable UUID marbleshopOrderId) throws Exception {
        MarbleshopOrder order = marbleshopOrderService.findById(marbleshopOrderId);
        byte[] pdfContent = pdfService.generateOrderPdf(order);
        String[] splittedId = order.getId().toString().split("-");
        String shortId = splittedId[splittedId.length - 1];
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=orcamento- " + shortId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfContent);
    }
}
