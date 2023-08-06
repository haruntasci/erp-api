package com.example.erp.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class OrderItemRequest {
    private String productName;
    private int quantity;

}
