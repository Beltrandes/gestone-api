package com.gestone.gestone_api.domain.order;

import com.gestone.gestone_api.domain.quotation.QuotationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/order")
public class MarbleshopOrderController {

    @Autowired
    private MarbleshopOrderService marbleshopOrderService;


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

}
