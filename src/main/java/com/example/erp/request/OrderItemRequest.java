package com.example.erp.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemRequest {
    private UUID orderUUID;
    private String productName;
    private int quantity;

}
