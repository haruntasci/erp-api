package com.example.erp.request;

import lombok.Data;

@Data
public class StockRequest {
    private String productName;
    private int quantity;
}
