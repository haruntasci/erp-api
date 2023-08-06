package com.example.erp.controller;

import com.example.erp.model.Bill;
import com.example.erp.request.BillRequest;
import com.example.erp.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {
        return new ResponseEntity<>(billService.getAllBills(), HttpStatus.OK);
    }

    @GetMapping("/order-uuid{uuid}")
    public ResponseEntity<Bill> getBillByOrderUUID(@PathVariable UUID uuid) {
        return new ResponseEntity<>(billService.getBillByOrderUUID(uuid), HttpStatus.OK);
    }
}
