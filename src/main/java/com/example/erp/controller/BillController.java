package com.example.erp.controller;

import com.example.erp.model.Bill;
import com.example.erp.request.BillRequest;
import com.example.erp.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping
    public ResponseEntity<Bill> createBill(BillRequest billRequest) {
        return new ResponseEntity<>(billService.createOneBill(billRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {
        return new ResponseEntity<>(billService.getAllBills(), HttpStatus.OK);
    }

}
