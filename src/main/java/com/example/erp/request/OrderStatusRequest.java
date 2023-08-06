package com.example.erp.request;

import com.example.erp.enums.OrderStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderStatusRequest {
    private OrderStatus status;
    private UUID orderUUID;
}
