package com.example.erp.controller;

import com.example.erp.model.Stock;
import com.example.erp.request.StockRequest;
import com.example.erp.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService service) {
        this.stockService = service;
    }

    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody StockRequest stock) {
        return new ResponseEntity<>(stockService.createOneStock(stock), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        return new ResponseEntity<>(stockService.getAllStocks(), HttpStatus.OK);
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deleteProductByUUID(@PathVariable UUID uuid) {
        return new ResponseEntity<>(stockService.deleteProductByUUID(uuid), HttpStatus.OK);
    }
}
