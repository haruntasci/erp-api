package com.example.erp.request;

import lombok.Data;

import java.util.UUID;

@Data
public class StockRequest {
    private UUID productUUID;
    private int quantity;
}
