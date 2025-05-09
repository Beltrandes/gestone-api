package com.gestone.gestone_api.domain.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
