package com.gestone.gestone_api.domain.bill;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/bill")
public class BillController {
    @Autowired
    private BillService billService;
    @PostMapping
    public ResponseEntity<BillResponseDTO> saveBill(@RequestBody BillRequestDTO billRequestDTO, HttpServletRequest request) {
        var savedBill = billService.save(billRequestDTO, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BillResponseDTO(savedBill));
    }
    @GetMapping("/{marbleshopId}")
    public ResponseEntity<List<BillResponseDTO>> listAllBills(@PathVariable UUID marbleshopId) {
        return ResponseEntity.ok().body(billService.findAll(marbleshopId).stream().map(BillResponseDTO::new).toList());
    }
}
