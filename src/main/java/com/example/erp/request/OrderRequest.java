package com.example.erp.request;

import lombok.Data;

@Data
public class OrderRequest {
    private String customerName;
    private String productName;
    private int quantity;
}
