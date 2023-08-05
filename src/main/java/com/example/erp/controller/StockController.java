package com.example.erp.controller;

import com.example.erp.model.Stock;
import com.example.erp.request.StockRequest;
import com.example.erp.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockService service;

    public StockController(StockService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody StockRequest stock) {
        return new ResponseEntity<>(service.createOneStock(stock), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        return new ResponseEntity<>(service.getAllStocks(), HttpStatus.OK);
    }
}
